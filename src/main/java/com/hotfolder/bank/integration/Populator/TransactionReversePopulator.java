package com.hotfolder.bank.integration.Populator;

import com.hotfolder.bank.service.TransactionService;
import com.hotfolder.bank.service.model.Batch;
import com.hotfolder.bank.service.model.Transaction;
import com.hotfolder.bank.service.model.TransactionDescription;
import com.opencsv.CSVReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Handles the operation of populating the DB from the CSV File.
 */
public class TransactionReversePopulator
{
	private static final Logger LOG = LoggerFactory.getLogger(TransactionReversePopulator.class);
	public static final String YY_MM_DD = "yy/MM/dd";

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TransactionService transactionService;

	/**
	 * @param file
	 * @param csvReader
	 * @throws IOException
	 */
	public void populate(final File file, final CSVReader csvReader) throws IOException
	{

		final Session session = sessionFactory.openSession();
		final org.hibernate.Transaction tx = beginTransaction(session);

		String[] csvTransaction;

		Batch batch = createAndGetBatch(file);
		//save partially the batch
		session.save(batch);
		commit(tx);


		int totalNumberOfRows = 0;
		int numberOfNewTrans = 0;
		//reading csv rows..
		while ((csvTransaction = csvReader.readNext()) != null)
		{
			//binding from CSV .
			final String transactionId = csvTransaction[0];
			final String transactionDate = csvTransaction[1];
			final String csvTransactionDescription = csvTransaction[2];
			final String csvAmount = csvTransaction[3];

			if (getTransactionByNK(transactionId) == null)
			{
				//count all new transactions
				numberOfNewTrans++;
				batch.setCreationDate(new java.util.Date());
				session.save(batch);
				commit(tx);
			}

			final TransactionDescription transactionDescription = getTransactionDescription(batch, csvTransactionDescription,
					session, tx);
			createTransaction(batch, transactionId, transactionDate, csvAmount, transactionDescription, session, tx);

			totalNumberOfRows++;
		}

		updateBatch(batch, totalNumberOfRows, numberOfNewTrans);

		session.save(batch);
		tx.commit();
		session.close();
	}

	private Transaction getTransactionByNK(final String transactionId)
	{
		return transactionService.findTransactionByNK(Integer.parseInt(transactionId));
	}

	private void updateBatch(final Batch batch, final int totalNumberOfRows, final int numberOfNewTrans)
	{
		batch.setTotalNumberOfRows(totalNumberOfRows);
		batch.setNumberOfNewTrans(numberOfNewTrans);
		batch.setEndDate(new Date());
	}

	private Batch createAndGetBatch(final File file)
	{
		Batch batch = new Batch();
		batch.setFileName(file.getName());
		batch.setBatchNK(file.getName());
		batch.setStartDate(new Date());
		return batch;
	}

	private void commit(final org.hibernate.Transaction tx)
	{
		tx.commit();
		tx.begin();
	}

	private void createTransaction(final Batch batch, final String transactionId, final String transactionDate,
			final String csvAmount, final TransactionDescription transactionDescription, final Session session,
			final org.hibernate.Transaction tx)
	{

		Transaction transaction = new Transaction();

		final int transactionNk = Integer.parseInt(transactionId);
		final Transaction transactionByNK = transactionService.findTransactionByNK(transactionNk);
		removeTransactionIfExists(transactionNk, transactionByNK);

		transaction.setTransactionID(transactionNk);
		transaction.setTransactionNK(transactionNk);
		transaction.setTransactionDate(dateParser(transactionDate));
		transaction.setAmount((long) Double.parseDouble(csvAmount));
		transaction.setCreationDate(new java.util.Date());
		transaction.setBatchPK(batch);
		transaction.setTransactionDescriptionPK(transactionDescription);
		session.save(transaction);

		commit(tx);

	}

	private void removeTransactionIfExists(final int transactionNk, final Transaction transactionByNK)
	{
		if (transactionByNK != null)
		{
			transactionService.deleteTransactionByNK(transactionNk);
		}
	}



	private TransactionDescription getTransactionDescription(final Batch batch, final String csvTransactionDescription,
			final Session session, final org.hibernate.Transaction tx)
	{

		TransactionDescription transactionDescription = new TransactionDescription();

		final TransactionDescription transactionDescriptionByNK = transactionService
				.findTransactionDescriptionByNK(csvTransactionDescription);

		removeTransactionDescriptionIfExists(csvTransactionDescription, transactionDescriptionByNK);

		//set fields
		transactionDescription.setDescription(csvTransactionDescription);
		transactionDescription.setTransactionDescriptionNK(csvTransactionDescription);
		transactionDescription.setCreationDate(new java.util.Date());
		transactionDescription.setBatchPk(batch);

		session.save(transactionDescription);
		commit(tx);

		return transactionDescription;
	}

	private void removeTransactionDescriptionIfExists(final String csvTransactionDescription,
			final TransactionDescription transactionDescriptionByNK)
	{
		if (transactionDescriptionByNK != null)
		{
			transactionService.deleteTransactionDescriptionByNK(csvTransactionDescription);
		}
	}

	private Date dateParser(final String transactionDate)
	{
		Date date = null;
		try
		{
			date = new SimpleDateFormat(YY_MM_DD).parse(transactionDate);

		}
		catch (ParseException e)
		{
			LOG.error("error occurred during parsing",e);
		}
		return date;
	}

	private org.hibernate.Transaction beginTransaction(final Session session)
	{
		return session.beginTransaction();
	}

}

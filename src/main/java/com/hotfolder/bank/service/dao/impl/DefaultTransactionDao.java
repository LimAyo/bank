package com.hotfolder.bank.service.dao.impl;

import com.hotfolder.bank.service.dao.TransactionDao;
import com.hotfolder.bank.service.model.TransactionDescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * The Default implementation of TransactionDao.
 */

@Repository("transactionDao")
public class DefaultTransactionDao implements TransactionDao
{
	Logger logger = LoggerFactory.getLogger(DefaultTransactionDao.class);

	public static final String DELETE_FROM_TRANSACTIONDESCRIPTION = "delete from transactiondescription where transactiondescriptionnk = :transDescNK";

	public static final String DELETE_FROM_TRANSACTION = "delete  from transaction where transactionnk  = :transNK";

	public static final String SQL =
			"SELECT trans.transactionid,trans.transactiondate,trans.amount,descr.description,batch.startdate,batch.enddate,batch.filename "
					+ " FROM transaction as trans \n"
					+ " JOIN transactiondescription as descr ON trans.transactiondescriptionpk=descr.transactiondescriptionpk \n"
					+ " JOIN batch as batch ON trans.batchpk=batch.batchpk";


	public static final String FIND_TRANS_BY_NK =
			"SELECT * from transaction where transactionnk  = :transNK";


	public static final String FIND_TRANS_DECS_BY_NK =
			"SELECT * from transactiondescription where transactiondescriptionnk = :transDescNK";

	@Autowired
	private SessionFactory sessionFactory;


	@Override public List<Object[]> findTransactions()
	{
		final Session session = sessionFactory.openSession();
		final org.hibernate.Transaction transaction = session.beginTransaction();

		final NativeQuery query = session.createSQLQuery(SQL);

		final List<Object[]> list = query.list();
		commit(session, transaction);
		return list != null ? list : new ArrayList<>();
	}

	@Override public com.hotfolder.bank.service.model.Transaction findTransactionByNK(final int transcNK)
	{
		final Session session = sessionFactory.openSession();
		final org.hibernate.Transaction transaction = session.beginTransaction();
		List<com.hotfolder.bank.service.model.Transaction> bankTransactions = null;
		try
		{
			final NativeQuery query = session.createSQLQuery(FIND_TRANS_BY_NK);
			query.setParameter("transNK", transcNK);
			query.addEntity(com.hotfolder.bank.service.model.Transaction.class);
			bankTransactions = query.list();
		}
		catch (EntityNotFoundException e)
		{
			logger.error("Error occured while getting Transactionwith nk :" + transcNK + e);
		}
		commit(session, transaction);

		return !CollectionUtils.isEmpty(bankTransactions) ? bankTransactions.get(0) : null;
	}




	@Override public TransactionDescription findTransactionDescriptionByNK(final String transDescNK)
	{
		final Session session = sessionFactory.openSession();
		final org.hibernate.Transaction transaction = session.beginTransaction();
		List<TransactionDescription> transactionDescriptions = null;
		try
		{
			final NativeQuery query = session.createSQLQuery(FIND_TRANS_DECS_BY_NK);
			query.setParameter("transDescNK", transDescNK);
			query.addEntity(TransactionDescription.class);
			transactionDescriptions = query.list();

		}
		catch (EntityNotFoundException e)
		{
			logger.error("Error occured while getting TransactionDescription with nk :" + transDescNK + e);
		}
		commit(session, transaction);
		return !CollectionUtils.isEmpty(transactionDescriptions) ? transactionDescriptions.get(0) : null;
	}

	@Override public void deleteTransactionByNK(final int transNK)
	{
		final Session session = sessionFactory.openSession();
		final org.hibernate.Transaction transaction = session.beginTransaction();


		final NativeQuery query = session.createSQLQuery(DELETE_FROM_TRANSACTION);
		query.setParameter("transNK", transNK);
		query.executeUpdate();
		commit(session, transaction);

	}

	@Override public void deleteTransactionDescriptionByNK(final String transDescNK)
	{
		final Session session = sessionFactory.openSession();
		final org.hibernate.Transaction transaction = session.beginTransaction();


		final NativeQuery query = session.createSQLQuery(
				DELETE_FROM_TRANSACTIONDESCRIPTION);
		query.setParameter("transDescNK", transDescNK);
		query.executeUpdate();
		commit(session, transaction);

	}
	
	private void commit(final Session session, final Transaction transaction)
	{
		transaction.commit();
		session.close();
	}

}

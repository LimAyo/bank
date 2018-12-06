package com.hotfolder.bank.service.impl;

import com.hotfolder.bank.service.Helper.TransactionHelper;
import com.hotfolder.bank.service.TransactionService;
import com.hotfolder.bank.service.dao.TransactionDao;
import com.hotfolder.bank.service.model.Transaction;
import com.hotfolder.bank.service.model.TransactionDescription;
import com.hotfolder.bank.service.pojo.TransactionElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("transactionService")
public class DefaultTransactionService implements TransactionService
{
	@Autowired
	private TransactionDao transactionDao;

	@Override public List<TransactionElement> retrieveTransactions()
	{


		final List<Object[]> transactions = transactionDao.findTransactions();
		final List<TransactionElement> TransactionElementList = new ArrayList<>();
		for (Object[] transactionObject : transactions)
		{
			TransactionElement transactionElement = TransactionHelper.createTransaction(transactionObject);

			TransactionElementList.add(transactionElement);
		}

		return TransactionElementList;
	}

	@Override public Transaction findTransactionByNK(final int transcNK)
	{
		return transactionDao.findTransactionByNK(transcNK);
	}

	@Override public TransactionDescription findTransactionDescriptionByNK(final String transDescNK)
	{
		return transactionDao.findTransactionDescriptionByNK(transDescNK);
	}

	@Override public void deleteTransactionByNK(final int transDescNK)
	{
		 transactionDao.deleteTransactionByNK(transDescNK);
	}

	@Override public void deleteTransactionDescriptionByNK(final String transDescNK)
	{
		 transactionDao.deleteTransactionDescriptionByNK(transDescNK);
	}





}

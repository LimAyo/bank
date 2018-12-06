package com.hotfolder.bank.service;

import com.hotfolder.bank.service.model.Transaction;
import com.hotfolder.bank.service.model.TransactionDescription;
import com.hotfolder.bank.service.pojo.TransactionElement;

import java.util.List;



public interface TransactionService
{

	List<TransactionElement> retrieveTransactions();

	Transaction findTransactionByNK(final int transcNK);

	TransactionDescription findTransactionDescriptionByNK(final String transDescNK);

	void deleteTransactionByNK(final int transDescNK);

	void deleteTransactionDescriptionByNK(final String transDescNK);
}

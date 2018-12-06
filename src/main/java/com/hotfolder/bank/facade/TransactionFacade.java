package com.hotfolder.bank.facade;

import com.hotfolder.bank.service.pojo.TransactionElement;

import java.util.List;


/**
 * Represents the Facade Layer.
 */
public interface TransactionFacade
{

	/**
	 * fetch Transactions.
	 * @return List of transactions.
	 */
	List<TransactionElement> fetchTransactions();
}

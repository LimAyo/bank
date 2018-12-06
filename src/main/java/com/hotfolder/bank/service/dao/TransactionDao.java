package com.hotfolder.bank.service.dao;

import com.hotfolder.bank.service.model.Transaction;
import com.hotfolder.bank.service.model.TransactionDescription;

import java.util.List;


/**
 * Represents the DAO of the bank app
 */
public interface TransactionDao
{

	/**
	 * find Transactions
	 *
	 * @return list of transactions
	 */
	List<Object[]> findTransactions();

	/**
	 * find Transaction By NK
	 *
	 * @param transcNK
	 * @return Transaction
	 */
	Transaction findTransactionByNK(final int transcNK);

	/**
	 * find Transaction Description By NK
	 *
	 * @param transDescNK
	 * @return TransactionDescription
	 */
	TransactionDescription findTransactionDescriptionByNK(final String transDescNK);

	/**
	 * delete Transaction By NK
	 *
	 * @param transDescNK
	 */
	void deleteTransactionByNK(final int transDescNK);

	/**
	 * delete TransactionDescription By NK
	 *
	 * @param transDescNK
	 */
	void deleteTransactionDescriptionByNK(final String transDescNK);
}

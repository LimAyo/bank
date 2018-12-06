package com.hotfolder.bank.facade.impl;

import com.hotfolder.bank.facade.TransactionFacade;
import com.hotfolder.bank.service.TransactionService;
import com.hotfolder.bank.service.pojo.TransactionElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/** Default implementation of TransactionFacade
 * @author Ayoub Lim
 */
@Component("transactionFacade")
public class DefaultTransactionFacade implements TransactionFacade
{
	@Autowired
	TransactionService transactionService;

	@Override
	public List<TransactionElement> fetchTransactions(){

		return transactionService.retrieveTransactions();
	}
}

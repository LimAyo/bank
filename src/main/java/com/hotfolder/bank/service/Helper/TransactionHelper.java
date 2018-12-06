package com.hotfolder.bank.service.Helper;

import com.hotfolder.bank.service.pojo.TransactionElement;

import java.util.Date;


public final class TransactionHelper
{
	private   TransactionHelper()
	{

	}

	public static TransactionElement createTransaction(final Object[] transactionObject)
	{
		return new TransactionElement(
				Integer.parseInt(transactionObject[0].toString()),
				(Date) (transactionObject[1]),
				Long.parseLong(transactionObject[2].toString()),
				transactionObject[3].toString(),
				(Date) (transactionObject[4]),
				(Date) (transactionObject[5]),
				transactionObject[6].toString()
		);
	}
}

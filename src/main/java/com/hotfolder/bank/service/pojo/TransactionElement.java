package com.hotfolder.bank.service.pojo;

import java.util.Date;


public class TransactionElement
{
	private Integer transactionID;
	private Date transactionDate;
	private Long amount;
	private String description;
	private Date startDate;
	private Date endDate;
	private String fileName;

	public TransactionElement(final Integer transactionID, final Date transactionDate, final Long amount, final String description,
			final Date startDate,
			final Date endDate, final String fileName)
	{
		this.transactionID = transactionID;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fileName = fileName;
	}

	public Integer getTransactionID()
	{
		return transactionID;
	}

	public Date getTransactionDate()
	{
		return transactionDate;
	}

	public Long getAmount()
	{
		return amount;
	}

	public String getDescription()
	{
		return description;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public String getFileName()
	{
		return fileName;
	}
}

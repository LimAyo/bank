package com.hotfolder.bank.service.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transactionpk", nullable = false)
	private int transactionPK;

	@Column(name = "transactionnk", nullable = false)
	private int transactionNK;

	@Column(name = "TransactionID", unique = true)
	private int transactionID;

	@Column(name = "transactiondate")
	private Date transactionDate;

	@Column(name = "amount")
	private Long amount;

	@Column(name = "creationdate", nullable = false)
	private Date creationDate;

	@ManyToOne(targetEntity = TransactionDescription.class)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "transactionDescriptionpk", nullable = false)
	private TransactionDescription transactionDescriptionPK;

	@ManyToOne(targetEntity=Batch.class)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "batchpk", nullable = false)
	private Batch batchPK;

	public int getTransactionPK()
	{
		return transactionPK;
	}

	public int getTransactionNK()
	{
		return transactionNK;
	}

	public int getTransactionID()
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

	public Date getCreationDate()
	{
		return creationDate;
	}

	public TransactionDescription getTransactionDescriptionPK()
	{
		return transactionDescriptionPK;
	}

	public Batch getBatchPK()
	{
		return batchPK;
	}

	public void setTransactionPK(final int transactionPK)
	{
		this.transactionPK = transactionPK;
	}

	public void setTransactionNK(final int transactionNK)
	{
		this.transactionNK = transactionNK;
	}

	public void setTransactionID(final int transactionID)
	{
		this.transactionID = transactionID;
	}

	public void setTransactionDate(final Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public void setAmount(final Long amount)
	{
		this.amount = amount;
	}

	public void setCreationDate(final Date creationDate)
	{
		this.creationDate = creationDate;
	}

	public void setTransactionDescriptionPK(final TransactionDescription transactionDescriptionPK)
	{
		this.transactionDescriptionPK = transactionDescriptionPK;
	}

	public void setBatchPK(final Batch batchPK)
	{
		this.batchPK = batchPK;
	}
}

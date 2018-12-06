package com.hotfolder.bank.service.model;



import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "transactiondescription")
public class TransactionDescription
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transactiondescriptionpk" , nullable = false)
	private int transactionDescriptionPK;

	@Column(name = "transactiondescriptionnk" , nullable = false)
	private String transactionDescriptionNK;

	@Column(name = "description" , unique = true)
	private String description;

	@Column(name = "creationdate" , nullable = false)
	private Date creationDate;

	@ManyToOne(targetEntity=Batch.class)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "batchpk",  nullable = false)
	private Batch batchPk;


	public int getTransactionDescriptionPK()
	{
		return transactionDescriptionPK;
	}

	public String getTransactionDescriptionNK()
	{
		return transactionDescriptionNK;
	}

	public String getDescription()
	{
		return description;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public Batch getBatchPk()
	{
		return batchPk;
	}

	public void setTransactionDescriptionPK(final int transactionDescriptionPK)
	{
		this.transactionDescriptionPK = transactionDescriptionPK;
	}

	public void setTransactionDescriptionNK(final String transactionDescriptionNK)
	{
		this.transactionDescriptionNK = transactionDescriptionNK;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public void setCreationDate(final Date creationDate)
	{
		this.creationDate = creationDate;
	}

	public void setBatchPk(final Batch batchPk)
	{
		this.batchPk = batchPk;
	}
}

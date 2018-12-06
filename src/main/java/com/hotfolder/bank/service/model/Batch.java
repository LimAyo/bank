package com.hotfolder.bank.service.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "batch")
public class Batch
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "batchpk", nullable = false)
	private int batchPK;

	@Column(name = "batchnk", nullable = false)
	private String batchNK;

	@Column(name = "filename")
	private String fileName;

	@Column(name = "totalnumberofrows")
	private int totalNumberOfRows;

	@Column(name = "numberofnewtrans")
	private int numberOfNewTrans;

	@Column(name = "startdate")
	private Date startDate;

	@Column(name = "enddate")
	private Date endDate;

	@Column(name = "creationdate")
	private Date creationDate;

	public int getBatchPK()
	{
		return batchPK;
	}

	public String getBatchNK()
	{
		return batchNK;
	}

	public String getFileName()
	{
		return fileName;
	}

	public int getTotalNumberOfRows()
	{
		return totalNumberOfRows;
	}

	public int getNumberOfNewTrans()
	{
		return numberOfNewTrans;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setBatchPK(final int batchPK)
	{
		this.batchPK = batchPK;
	}

	public void setBatchNK(final String batchNK)
	{
		this.batchNK = batchNK;
	}

	public void setFileName(final String fileName)
	{
		this.fileName = fileName;
	}

	public void setTotalNumberOfRows(final int totalNumberOfRows)
	{
		this.totalNumberOfRows = totalNumberOfRows;
	}

	public void setNumberOfNewTrans(final int numberOfNewTrans)
	{
		this.numberOfNewTrans = numberOfNewTrans;
	}

	public void setStartDate(final Date startDate)
	{
		this.startDate = startDate;
	}

	public void setEndDate(final Date endDate)
	{
		this.endDate = endDate;
	}

	public void setCreationDate(final Date creationDate)
	{
		this.creationDate = creationDate;
	}
}

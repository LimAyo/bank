package com.hotfolder.bank.integration.task;

import com.hotfolder.bank.integration.job.TransationJob;
import org.springframework.messaging.Message;

import java.io.File;


public class SampleImportTask
{
	private TransationJob transationJob;

	public Message<File> process(final Message<File> message)
	{
		//invoke the job .
		transationJob.perform(message);

		return message;

	}

	public void setTransationJob(TransationJob transationJob)
	{
		this.transationJob = transationJob;
	}


}

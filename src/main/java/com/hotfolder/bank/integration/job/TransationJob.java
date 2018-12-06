package com.hotfolder.bank.integration.job;

import com.hotfolder.bank.integration.processor.TransactionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.Message;

import java.io.File;


/**
 * Represents the associated Job of Importing the CSV file.
 */
public class TransationJob
{
	private static final Logger LOG = LoggerFactory.getLogger(TransationJob.class);

	TransactionProcessor transactionProcessor;

	public String perform(Message<File> message)
	{

		if (message.getPayload() == null)
		{
			LOG.error("Could not Open CSV File");
			return "ERROR";
		}
		else
		{

			final String result = transactionProcessor.launchProcess(
					message.getPayload());
			return result;
		}
	}

	@Required
	public void setTransactionProcessor(final TransactionProcessor transactionProcessor)
	{
		this.transactionProcessor = transactionProcessor;
	}
}

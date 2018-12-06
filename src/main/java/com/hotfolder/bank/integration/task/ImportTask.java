package com.hotfolder.bank.integration.task;

import org.springframework.messaging.Message;

import java.io.File;


/**
 * The Associated Task to  import the file.
 */
public interface ImportTask
{
	/**
	 * Executes a task with a Message
	 *
	 * @param message
	 * @return the message
	 * @throws Exception
	 */
	void process(Message<File> message) throws Exception;
}

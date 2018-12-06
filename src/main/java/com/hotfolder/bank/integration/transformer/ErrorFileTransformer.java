package com.hotfolder.bank.integration.transformer;

import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;


/**
 * The transformer of Error files.
 */
public class ErrorFileTransformer extends AbstractTransformer
{

	@Override
	protected Object doTransform(final Message<?> message) throws Exception
	{
		final MessagingException payload = (MessagingException) message.getPayload();
		return payload.getFailedMessage();
	}
}

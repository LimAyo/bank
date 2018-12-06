package com.hotfolder.bank.integration.transformer;

import com.hotfolder.bank.util.RegexParser;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;


/**
 * The File importer, regenerate another file for the next chain.
 */
public class ImportFilesTransformer extends AbstractTransformer
{



	@Override
	protected Object doTransform(final Message<?> message)
	{

		return message;
	}

}

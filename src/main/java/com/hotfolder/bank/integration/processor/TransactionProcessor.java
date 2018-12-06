/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hotfolder.bank.integration.processor;

import com.hotfolder.bank.integration.Populator.TransactionReversePopulator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;


/**
 * Rpresents the transaction processor.
 */
public class TransactionProcessor
{

	private TransactionReversePopulator transactionReversePopulator;

	private static final Logger LOG = LoggerFactory.getLogger(TransactionProcessor.class);


	public String launchProcess(File file)
	{
		if (file != null)
		{
			try {

				FileReader filereader = new FileReader(file);
				//skip the header file.
				CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
				transactionReversePopulator.populate(file, csvReader);

			}
			catch (Exception e) {
				LOG.error("Technical error : " + e);
				//send to error.
				return "FAILURE";
			}

		}
		//send to archive.
		return "SUCCESS";
	}

	public void setTransactionReversePopulator(
			final TransactionReversePopulator transactionReversePopulator)
	{
		this.transactionReversePopulator = transactionReversePopulator;
	}
}

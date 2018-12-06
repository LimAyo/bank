package com.hotfolder.bank.controller;

import com.hotfolder.bank.facade.TransactionFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

/** Controller of the Bank web App.
 * @author Ayoub Lim
 */
@Controller
public class TransactionController
{

	@Resource
	private TransactionFacade transactionFacade;

	@RequestMapping("/")
	public String home(Map<String, Object> model) {

		model.put("transactions", transactionFacade.fetchTransactions());
		return "welcome";
	}
}

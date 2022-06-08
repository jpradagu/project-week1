package com.nttdata.bootcamp.productdomain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.productdomain.service.BankLoanService;

@RestController
@RequestMapping("/api/bank-loan")
public class BankLoanController {

	@Autowired
	private BankLoanService loanService;
}

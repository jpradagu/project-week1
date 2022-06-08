package com.nttdata.bootcamp.productdomain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeAccountBank {
	//PERSONAL_CREDIT("PersonalCredit"), ENTERPRISE_CREDIT("EnterpriseCredit"), 
	SAVING_ACCOUNT("SavingAccount"),
	CHECKING_ACCOUNT("CheckingAccount"), TIME_DEPOSIT_ACCOUNT("TimeDespositAccount");
	private String name;
}

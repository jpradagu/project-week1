package com.nttdata.bootcamp.productdomain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Product {

	private String id;
	private String customerId;
	private String cardNumber;
	private Date date;
	private ProductType productType;
	private BigDecimal amount;
	private List<String> members;
	private List<String> signatories;
}

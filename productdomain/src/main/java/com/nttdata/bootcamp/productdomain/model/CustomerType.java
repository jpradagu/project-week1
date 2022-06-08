package com.nttdata.bootcamp.productdomain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerType {
	PERSONAL("Personal"), ENTERPRISE("Enterprise");
	private String name;
}

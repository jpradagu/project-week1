package com.nttdata.bootcamp.customerdomain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusType {

	ENABLED("Enabled"),DISABLED("Disabled");
	private String name;
}

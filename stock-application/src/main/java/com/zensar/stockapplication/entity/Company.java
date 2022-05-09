package com.zensar.stockapplication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Company {

	private int companyId;
	private String companyName;
	private String companyCity;
	private long companyRevenue;
}

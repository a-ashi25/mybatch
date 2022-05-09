package com.zensar.stockapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Automatically Creates all the Getters,Setters,ArgsConstructors,
@NoArgsConstructor // Creates a default constructor
@AllArgsConstructor //Creates all arguments constructor
public class StockDTO {

	private long stockId;
	private String stockName;
	private String marketName;
	private double stockPrice;
}

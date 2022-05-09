package com.zensar.stockapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Automatically Creates all the Getters,Setters,ArgsConstructors,
@NoArgsConstructor // Creates a default constructor
@AllArgsConstructor // Creates all arguments constructor
//@ApiModel("this is stock model")
@Entity(name = "MyStock")
@Table(name = "Stock")

/*
 * @NamedQueries({
 * 
 * @NamedQuery(name = "Stock.findStockByNameAndPrice", query =
 * "FROM MyStock s where s.stockName=?1 and s.stockPrice=?2") })
 * 
 * //@NamedQuery(name = "Stock.findStockByName", query =
 * "FROM MyStock s where s.stockName=?1")
 * 
 * @NamedNativeQuery(name = "Stock.findStockByName", query =
 * "select * from stock where stock_name=?1", resultClass = Stock.class)
 */

public class Stock {

	// @ApiModelProperty("stockid is of int type")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private long stockId;
	private String stockName;
	private String marketName;
	private double stockPrice;

}

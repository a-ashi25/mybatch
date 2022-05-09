package com.zensar.stockapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zensar.stockapplication.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query(value = "select * from stock where stock_name=:stockName",nativeQuery = true)
	List<Stock> findStockByName(@Param("stockName")String stockName);
	
	@Query(value = "select * from stock where stock_name=:stockName and stock_price=:stockPrice",nativeQuery = true)
	List<Stock> findStockByNameAndPrice(@Param("stockName")String stockName,@Param("stockPrice") double stockPrice);

	
	
	
}

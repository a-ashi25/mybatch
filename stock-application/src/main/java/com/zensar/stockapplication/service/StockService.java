package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.dto.StockDTO;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;

public interface StockService {

	 List<StockDTO> getAllStocks(int pageNumber,int pageSize,String sortBy);
	
	 StockDTO getStock(long stockId) throws InvalidStockIdException ;
	
	 List<StockDTO> getStockByName(String stockName);
	
	 List<StockDTO> getStockByNameAndPrice(String stockName,double stockPrice);
	
	 StockDTO createStock(StockDTO stock,String token);
	
	 String deleteStock(long stockId);
	
	 String deleteAllStocks();
	
	 StockDTO updateStock(long stockId, StockDTO stock) throws InvalidStockIdException;
}

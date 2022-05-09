package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.dto.StockDTO;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

//	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StockDTO> getAllStocks(int pageNumber, int pageSize, String sortBy) {

		Page<Stock> pageStock = stockRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
		List<Stock> content = pageStock.getContent();

		List<StockDTO> stockResponses = new ArrayList<>();

		for (Stock stock : content) {
			StockDTO mapToResponse = modelMapper.map(stock, StockDTO.class);
			stockResponses.add(mapToResponse);
		}
		return stockResponses;
	}

	public List<StockDTO> getStockByName(String stockName) {

		List<Stock> findStockByName = stockRepository.findStockByName(stockName);
		List<StockDTO> stocks = new ArrayList<StockDTO>();

		for (Stock st : findStockByName) {
			stocks.add(modelMapper.map(st, StockDTO.class));
		}
		return stocks;

	}

	public List<StockDTO> getStockByNameAndPrice(String stockName, double stockPrice) {

		List<Stock> findStockByNameAndPrice = stockRepository.findStockByNameAndPrice(stockName, stockPrice);
		List<StockDTO> stocks = new ArrayList<StockDTO>();

		for (Stock st : findStockByNameAndPrice) {
			stocks.add(modelMapper.map(st, StockDTO.class));
		}
		return stocks;

	}

	@Override
	public StockDTO getStock(long stockId) throws InvalidStockIdException {

		Optional<Stock> optionalStock = stockRepository.findById(stockId);

		Stock stock = null;
		if (optionalStock.isPresent()) {
			stock = optionalStock.get();
			return modelMapper.map(stock, StockDTO.class);
		} else {
			throw new InvalidStockIdException("Stock is not available with stock id: " + stockId);
		}

		/*
		 * StockResponse stockResponse = new StockResponse();
		 * stockResponse.setStockId(stock.getStockId());
		 * stockResponse.setMarketName(stock.getMarketName());
		 * stockResponse.setStockName(stock.getStockName());
		 * stockResponse.setStockPrice(stock.getStockPrice());
		 */

		/*
		 * for (Stock stock : stocks) if (stock.getStockId() == stockId) { return stock;
		 * } return null;
		 */
	}

	@Override
	public StockDTO createStock(StockDTO stockRequest, String token) {

		// Stock newStock = mapToStock(stock);

		Stock newStock = modelMapper.map(stockRequest, Stock.class);

		if (token.equals("aa66471")) {
			Stock save = stockRepository.save(newStock);
			return modelMapper.map(save, StockDTO.class);
		} else {
			return null;
		}

	}

	@Override
	public String deleteStock(long stockId) {

		stockRepository.deleteById(stockId);
		return "Stock Deleted Successfully : " + stockId;
		/*
		 * for (Stock stock : stocks) { if (stock.getStockId() == stockId)
		 * 
		 * { stocks.remove(stock);
		 * 
		 * }
		 * 
		 * } return "Sorry Stock Id is not Available";
		 */
	}

	@Override
	public String deleteAllStocks() {
		stockRepository.deleteAll();
		return "All Stocks Deleted";
	}

	@Override
	public StockDTO updateStock(long stockId, StockDTO stockDto) throws InvalidStockIdException {

		StockDTO stockResponse = getStock(stockId);

		// Stock stock2 = mapToEntity(stock);
		Stock stock2 = modelMapper.map(stockDto, Stock.class);
		if (stock2 != null) {
			stock2.setStockId(stockId);
			stock2.setStockName(stockDto.getStockName());
			stock2.setMarketName(stockDto.getMarketName());
			stock2.setStockPrice(stockDto.getStockPrice());

			Stock stock3 = stockRepository.save(stock2);
			return modelMapper.map(stock3, StockDTO.class);
		}
		return null;
		/*
		 * availableStock.setStockName(stock.getStockName());
		 * availableStock.setMarketName(stock.getMarketName());
		 * availableStock.setStockPrice(stock.getStockPrice()); return availableStock;
		 */
	}

	/*
	 * private Stock mapToEntity(StockDTO stockDto) { Stock newStock = new Stock();
	 * newStock.setMarketName(stockDto.getMarketName());
	 * newStock.setStockName(stockDto.getStockName());
	 * newStock.setStockPrice(stockDto.getStockPrice());
	 * 
	 * return newStock; }
	 */

	/*
	 * private StockDTO mapToDto(Stock stock) { StockDTO stockResponse = new
	 * StockDTO();
	 * 
	 * stockResponse.setStockId(stock.getStockId());
	 * stockResponse.setStockName(stock.getStockName());
	 * stockResponse.setMarketName(stock.getMarketName());
	 * stockResponse.setStockPrice(stock.getStockPrice());
	 * 
	 * return stockResponse; }
	 */

}

package com.smallbiz.nasdaqalgo.service;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockHistoryService {

    private static final Logger LOGGER = LogManager.getLogger(StockHistoryService.class.getName());
    
    
	public void parseHistory() {

		

		try {
			Stock tesla = YahooFinance.get("TSLA", true);
			
			LOGGER.info( tesla.getHistory() );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	
}

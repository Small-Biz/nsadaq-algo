package com.smallbiz.nasdaqalgo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.smallbiz.nasdaqalgo.data.InstrumentTick;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

public class StockSnapshotService extends TimerTask {
	
    /** logger */
    private static final Logger LOGGER = LogManager.getLogger(StockSnapshotService.class.getName());

    private String instrumetCode;
    
//    private InstrumentTickDao instrumentTickDao = new InstrumentTickDaoImpl();
    
	public StockSnapshotService( String instrumentCode ) {
		
		this.instrumetCode = instrumentCode;
		
	}

	@Override
	public void run() {
		/*
		
		try {
			Stock stock = YahooFinance.get( instrumetCode );
			String symbol = stock.getSymbol();
			BigDecimal price = stock.getQuote().getPrice();
			BigDecimal previousClose = stock.getQuote().getPreviousClose();
			BigDecimal change = stock.getQuote().getChangeInPercent();
			BigDecimal peg = stock.getStats().getPeg();
			BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
			List<HistoricalQuote> history = stock.getHistory();
			
			
			LOGGER.info("stock: {}  prviousClose: {} price: {} change: {}", symbol, previousClose, price, change);	        
//			LOGGER.info("history: {} ", history);
			
			try {
				InstrumentTick instrumentTick = new InstrumentTick();
				
				instrumentTick.setInstrumentCode(instrumetCode);
				instrumentTick.setPrice(price);
				instrumentTick.setTickDate( DateTimeHelper.todayInServerDate() );
				instrumentTick.setTickTime( DateTimeHelper.nowInServerTime() );
				instrumentTick.setTickTimestamp( System.currentTimeMillis());
				
				instrumentTickDao.insert( instrumentTick );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	

	
}

package com.smallbiz.nasdaqalgo.service;

import java.math.BigDecimal;
import java.util.List;

import com.smallbiz.nasdaqalgo.data.InstrumentTick;

import yahoofinance.Stock;

public class TriggerCalculator {

	public static void triggerCalculate( Stock stock, List<InstrumentTick> todayTickList) {
		
		BigDecimal change = stock.getQuote().getChangeInPercent();
		String emailTitle = "";
		String emailMessage = "";
		
		if ( change.compareTo( new BigDecimal( 7 ) ) > 0 ) {
			//+7, need to sell
			
			
		}else if ( change.compareTo( new BigDecimal( -7 ) ) < 0 ) {
			//-7, need to buy
			
		}
		
	}
	
}

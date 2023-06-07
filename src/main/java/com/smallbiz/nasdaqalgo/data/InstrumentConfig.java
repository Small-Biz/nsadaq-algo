package com.smallbiz.nasdaqalgo.data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InstrumentConfig {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	private String instrumentCode;
	
	private BigDecimal totalLimit;
	
	private BigDecimal currentBalance;
	
	private BigDecimal eachTradeLimit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstrumentCode() {
		return instrumentCode;
	}

	public void setInstrumentCode(String instrumentCode) {
		this.instrumentCode = instrumentCode;
	}

	public BigDecimal getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(BigDecimal totalLimit) {
		this.totalLimit = totalLimit;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getEachTradeLimit() {
		return eachTradeLimit;
	}

	public void setEachTradeLimit(BigDecimal eachTradeLimit) {
		this.eachTradeLimit = eachTradeLimit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstrumentConfig [id=");
		builder.append(id);
		builder.append(", instrumentCode=");
		builder.append(instrumentCode);
		builder.append(", totalLimit=");
		builder.append(totalLimit);
		builder.append(", currentBalance=");
		builder.append(currentBalance);
		builder.append(", eachTradeLimit=");
		builder.append(eachTradeLimit);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}

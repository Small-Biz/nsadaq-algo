package com.smallbiz.nasdaqalgo.data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InstrumentTick {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String instrumentCode;
	
	private BigDecimal price;
	
	private int tickDate;
	
	private int tickTime;
	
	private long tickTimestamp;
	
	private long lastUpdateTimestamp;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getTickDate() {
		return tickDate;
	}

	public void setTickDate(int tickDate) {
		this.tickDate = tickDate;
	}

	public int getTickTime() {
		return tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	public long getTickTimestamp() {
		return tickTimestamp;
	}

	public void setTickTimestamp(long tickTimestamp) {
		this.tickTimestamp = tickTimestamp;
	}

	public long getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(long lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstrumentTick [id=");
		builder.append(id);
		builder.append(", instrumentCode=");
		builder.append(instrumentCode);
		builder.append(", price=");
		builder.append(price);
		builder.append(", tickDate=");
		builder.append(tickDate);
		builder.append(", tickTime=");
		builder.append(tickTime);
		builder.append(", tickTimestamp=");
		builder.append(tickTimestamp);
		builder.append(", lastUpdateTimestamp=");
		builder.append(lastUpdateTimestamp);
		builder.append("]");
		return builder.toString();
	}
	
	
}

package com.smallbiz.nasdaqalgo.repository;

import java.math.BigDecimal;

public interface OrderDao {
    public int createOrder(String instrumentCode, String side, BigDecimal price, long quantity );
}

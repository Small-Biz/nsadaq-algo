package com.smallbiz.nasdaqalgo.repository;

import com.smallbiz.nasdaqalgo.main.NasdasAlgo;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderDaoTest {

    public static void main(String[] args) {
        // ✅ Load Spring Application Context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NasdasAlgo.class);

        // ✅ Retrieve OrderDaoImpl from Spring Context
        OrderDaoImpl orderDao = context.getBean(OrderDaoImpl.class);

        // ✅ Test OrderDao
        int orderId = orderDao.createOrder("AAPL", "BUY", new BigDecimal("150.50"), 10);
        System.out.println("Created Order ID: " + orderId);
    }
}

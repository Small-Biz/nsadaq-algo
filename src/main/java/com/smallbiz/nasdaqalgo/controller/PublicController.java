package com.smallbiz.nasdaqalgo.controller;

import com.smallbiz.nasdaqalgo.repository.OrderDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Slf4j
@RequestMapping(path="/api")
@RestController
public class PublicController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping(path="/test")
    public ResponseEntity<?> test() {

        log.info("test");

//        int orderId = orderDao.createOrder("AAPL", "BUY", new BigDecimal("150.50"), 10);
//        System.out.println("Created Order ID: " + orderId);

        return ResponseEntity.ok("Success");

    }

}

package com.smallbiz.nasdaqalgo.repository;

import com.smallbiz.nasdaqalgo.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;

@Repository
public class OrderDaoImpl implements OrderDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SQL_CREATE_ORDER="INSERT INTO instrument_orders (date, instrument_code, side, price, quantity) VALUES (?,?,?,?,?)";

    @Override
    public int createOrder(String instrumentCode, String side, BigDecimal price, long quantity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(SQL_CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);

                        ps.setLong(1, DateTimeUtils.today());
                        ps.setString(2, instrumentCode);
                        ps.setString(3, side);
                        ps.setBigDecimal(4, price);
                        ps.setLong(5, quantity);

                        return ps;
                    }
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }

}

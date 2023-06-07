package com.smallbiz.nasdaqalgo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.smallbiz.nasdaqalgo.data.InstrumentConfig;
import com.smallbiz.nasdaqalgo.data.InstrumentConfigRepository;
import com.smallbiz.nasdaqalgo.data.UserRepository;

public class ConfigService {

    private static final Logger LOGGER = LogManager.getLogger(ConfigService.class.getName());

    @Autowired
    private InstrumentConfigRepository instrumentConfigRepository;
    
    @Autowired
    private UserRepository userRepository;

    
    public  List<InstrumentConfig> parseAllConfig() {
    	
    	LOGGER.info("instrumentConfigRepository: " + instrumentConfigRepository );
    	
    	LOGGER.info("userRepository.findAll(): " + userRepository.findAll() );
    	
    	
    	
    	return StreamSupport.stream(instrumentConfigRepository.findAll().spliterator(), false)
            	    .collect(Collectors.toList());

    }
    
}

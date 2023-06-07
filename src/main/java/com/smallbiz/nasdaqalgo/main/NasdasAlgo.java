package com.smallbiz.nasdaqalgo.main;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.smallbiz.nasdaqalgo.data.InstrumentConfig;
import com.smallbiz.nasdaqalgo.service.ConfigService;

@SpringBootApplication
@ComponentScan(basePackages = { "com.smallbiz.nasdaqalgo.controller"} )
@EnableJpaRepositories(basePackages ={ "com.smallbiz.nasdaqalgo.data"})    
@EntityScan(basePackages ={ "com.smallbiz.nasdaqalgo.data"})
public class NasdasAlgo {

	public static void main(String[] args) {
		SpringApplication.run(NasdasAlgo.class, args);
		
		ConfigService configService = new ConfigService();
		List<InstrumentConfig> instrumentConfigList = configService.parseAllConfig();
//		
//		LOGGER.info("instrumentConfigList: " + instrumentConfigList );
//		
//		StockHistoryService service = new StockHistoryService();
//		service.parseHistory();
//		
//		Timer timer = new Timer();
//
//		
//		timer.schedule(new StockSnapshotService("TSLA"), 0, 60*1000l);
	}
}

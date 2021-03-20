package com.selflearn.ecommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootEcommerceApplication {

	private static final Logger logger = LogManager.getLogger(SpringBootEcommerceApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(SpringBootEcommerceApplication.class, args);

		logger.trace("Trace Message!");
		logger.debug("Debug Message!");
		logger.info("Info Message!");
		logger.warn("Warn Message!");
		logger.error("Error Message!");
		logger.fatal("Fatal Message!");
	}

}

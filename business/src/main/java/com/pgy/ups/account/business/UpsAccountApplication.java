/**
 *
 */
package com.pgy.ups.account.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@SpringBootApplication
@ComponentScan("com.pgy.ups.**")
public class UpsAccountApplication {
    private static final Logger logger = LoggerFactory.getLogger(UpsAccountApplication.class);

    public static void main(String[] args) {
        logger.info ("begin to start-up [ups-account]");
        SpringApplication.run(UpsAccountApplication.class, args);
        logger.info ("start-up [ups-account] success !!!");
    }

}

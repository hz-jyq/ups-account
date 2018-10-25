package com.pgy.ups.account.business.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableTransactionManagement(proxyTargetClass=true)
public class TransactionConfiguration {
   
}

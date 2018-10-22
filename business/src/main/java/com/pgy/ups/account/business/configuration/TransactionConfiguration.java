package com.pgy.ups.account.business.configuration;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(mode=AdviceMode.ASPECTJ)//开启asjectJ代理，防止内部方法调用事务失效
public class TransactionConfiguration {

}

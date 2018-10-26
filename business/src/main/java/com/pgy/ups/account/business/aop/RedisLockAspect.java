package com.pgy.ups.account.business.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.pgy.ups.account.commom.utils.RedisUtils;

/**
 * ParamsLog注解记录入参和出参
 * @author 墨凉
 *
 */

@Aspect
@Component
public class RedisLockAspect implements Ordered{
	
	private Logger logger=LoggerFactory.getLogger(RedisLockAspect.class);
	
	@Resource
	private RedisUtils redisUtils;
    
	//@ParamLog在方法上或类上 注解拦截
	@Pointcut("@annotation(com.pgy.ups.account.commom.annotation.RedisLock)")
	public void redisLockPointcut() {}
	
	//记录入参回参日志
	@Around(value="redisLockPointcut()")
	public void lock(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
		try {			
			Object obj=joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		}
		
	}
    
	/**
	 * 定义烂机器顺序
	 */
	@Override
	public int getOrder() {
		return 1;
	}
	
}

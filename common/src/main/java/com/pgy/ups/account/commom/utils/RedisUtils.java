package com.pgy.ups.account.commom.utils;

import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtils {
	
	@Resource
	private JedisPool jedisPool; 
	
	private Jedis jedis=null;
	
	private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    
    private static final Long RELEASE_SUCCESS = 1L;
	
    public Long setnx(String key,String value) {
    	jedis=jedisPool.getResource(); 
    	Long result= jedis.setnx(key.getBytes(),value.getBytes()); 
    	return result;
    }	
    
    public String get(String key) {
    	jedis=jedisPool.getResource(); 
    	String value=jedis.get(key);
    	return  value;
    }
    
    public boolean setex(String key ,String value,int expire) {
    	jedis=jedisPool.getResource(); 
    	String result= jedis.setex(key.getBytes(),expire,value.getBytes()); 
    	return LOCK_SUCCESS.equals(result);
    }
    
    public boolean delete(String key) {
    	jedis=jedisPool.getResource(); 
    	Long result=jedis.del(key);
    	return result>0;
    }
    
    /**
     * @Description redis 分布式加锁
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public boolean redisLock(String lockKey, String requestId, int expireTime) {
    	jedis=jedisPool.getResource(); 
    	String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {       	
            return true;
        }
        return false;
    }
    
    /**
     * @Description redis 分布式解锁
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean redisUnLock(String lockKey, String requestId) {
    	jedis=jedisPool.getResource(); 
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}

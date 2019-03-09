package com.pxxysecondhand.component.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pxxysecondhand.component.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;


public class JedisClientSingle implements JedisClient {
	@Autowired
	private JedisPool jedisPool;
	
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public long hset(String key, String field, String value) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, field, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String field) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key, field);
		jedis.close();
		return result;
	}

	@Override
	public long incre(String key) {
		// TODO Auto-generated method stub
		Jedis jedis =jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long desc(String key) {
		// TODO Auto-generated method stub
		Jedis jedis =jedisPool.getResource();
		Long result = jedis.decr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int itemTimeOut) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, itemTimeOut);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		// TODO Auto-generated method stub
		Jedis jedis =jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String key, String field) {
		// TODO Auto-generated method stub
		Jedis jedis =jedisPool.getResource();
		Long result = jedis.hdel(key,field);
		jedis.close();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.JedisClient#scan(int, java.lang.String)
	 */
	@Override
	public List<String> scan(int cursor, String matchKey) {
		// TODO Auto-generated method stub
		Jedis jedis =jedisPool.getResource();
		ScanParams scanParams = new ScanParams();
		scanParams.match(matchKey);
		ScanResult<String> result = jedis.scan(cursor, scanParams);
		jedis.close();
		return result.getResult();
	}

}

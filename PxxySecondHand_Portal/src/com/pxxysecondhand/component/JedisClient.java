package com.pxxysecondhand.component;

import java.util.List;

public interface JedisClient {
	
	public String set(String key,String value);
	public String get(String key);
	public long hset(String key,String field,String value);
	public String hget(String key,String field);
	//将key中对应的value增加1，如果value不是数值，则抛出错误
	public long incre(String key);
	public long  desc(String key);
	//设置失效时间
	public long expire(String key,int itemTimeOut);
	//查看剩余失效时间  -2表示已经失效 -1表示永久保存
	public long ttl(String key);
	public long hdel(String key,String field);
	//从0查询所有的匹配键
	public List<String> scan(int cursor , String matchKey);
}

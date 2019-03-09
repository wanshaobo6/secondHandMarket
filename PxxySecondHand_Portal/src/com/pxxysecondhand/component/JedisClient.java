package com.pxxysecondhand.component;

import java.util.List;

public interface JedisClient {
	
	public String set(String key,String value);
	public String get(String key);
	public long hset(String key,String field,String value);
	public String hget(String key,String field);
	//��key�ж�Ӧ��value����1�����value������ֵ�����׳�����
	public long incre(String key);
	public long  desc(String key);
	//����ʧЧʱ��
	public long expire(String key,int itemTimeOut);
	//�鿴ʣ��ʧЧʱ��  -2��ʾ�Ѿ�ʧЧ -1��ʾ���ñ���
	public long ttl(String key);
	public long hdel(String key,String field);
	//��0��ѯ���е�ƥ���
	public List<String> scan(int cursor , String matchKey);
}

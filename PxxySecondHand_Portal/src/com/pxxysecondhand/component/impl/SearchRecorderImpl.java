/**   
* @Title: SearchRecorderImpl.java 
* @Package com.pxxysecondhand.component.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author ���ٲ�

* @date 2019��3��3�� ����8:10:33 
* @version V1.0   
*/
package com.pxxysecondhand.component.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.pxxysecondhand.component.ISearchRecorder;
import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.utils.JsonUtils;

/**
 * @author ���ٲ�
 *
 */
public class SearchRecorderImpl implements ISearchRecorder{
	
	@Autowired
	private JedisClient jedisClient;
	
	private List<String> populateSearchKey = new ArrayList();
	
	private long time = 0;
	
	@Value("${UPDATE_POPULATE_SEARCH_INTERVAL}")
	private long UPDATE_POPULATE_SEARCH_INTERVAL;
	
	
	//�����ؼ��ֵ��û���¼�洢ǰ׺
	@Value("${SEARCH_USER_PREFIX}")
	private String SEARCH_USER_PREFIX;
	
	//�����ؼ��������洢ǰ׺
	@Value("${SEARCH_KEYWORD_NUM_PREFIX}")
	private String SEARCH_KEYWORD_NUM_PREFIX;
	
	@Value("${SEARCH_RECORD_INTERVAL}")
	private long SEARCH_RECORD_INTERVAL;
	
	@Value("${POPULATE_SEARCH_KEY_NUM}")
	private int POPULATE_SEARCH_KEY_NUM;
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.ISearchRecorder#addRecord(java.lang.String, java.lang.String)
	 */
	@Override
	public void addRecord(String keyWords, String token) {
		// TODO Auto-generated method stub
		List<Long> recordList = new ArrayList(Arrays.asList(new Date().getTime()));
		//�ж�KeyWords Token�Ƿ�Ϊ��
		if(StringUtils.isEmpty(keyWords) || StringUtils.isEmpty(token)){
			return;
		}
		
		//�����û�ˢ�ȴ�
		String userSearchRecord = jedisClient.hget(SEARCH_USER_PREFIX+token,keyWords);
		 if(!StringUtils.isEmpty(userSearchRecord)) {
			 List<Long> searchTimeList = JsonUtils.jsonToList(userSearchRecord, Long.class);
			 recordList.addAll(searchTimeList);
			 if(System.currentTimeMillis()-searchTimeList.get(0) < SEARCH_RECORD_INTERVAL) {
				 return ; 
			 }
		 }
		//������������
		String searchNumStr = jedisClient.get(SEARCH_KEYWORD_NUM_PREFIX+keyWords);
		jedisClient.set(SEARCH_KEYWORD_NUM_PREFIX+keyWords, 
				String.valueOf(StringUtils.isEmpty(searchNumStr) ? 1 :Integer.valueOf(searchNumStr)+1));
		//�����û�������¼
		jedisClient.hset(SEARCH_USER_PREFIX+token,keyWords,JsonUtils.objectToJson(recordList));

	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.#getPopulteKeywords(int)
	 */
	@Override
	public List<String> getPopulteKeywords() {
		// TODO Auto-generated method stub
		//���time=0���� ����currtime-time�������  ���»�����ȴ���
		if(time == 0 || System.currentTimeMillis() - time > UPDATE_POPULATE_SEARCH_INTERVAL) {
			Map<String,Long> numMap = new HashMap();
			List<String> nameList = jedisClient.scan(0, SEARCH_KEYWORD_NUM_PREFIX+"*");
			if(CollectionUtils.isEmpty(nameList))
				return null; 
			nameList.forEach(name ->{ 
				long value = Long.valueOf(jedisClient.get(name)); 
				numMap.put(name, value);
				});
			//��Map����
			Map<String, Long> linkedMap = sortByValue(numMap);
			List<String> keySet = new ArrayList(linkedMap.keySet());
			int i = 0;
			populateSearchKey = new ArrayList();
			while(i<POPULATE_SEARCH_KEY_NUM && keySet.size()>0) {
				populateSearchKey.add(StringUtils.split(keySet.remove(keySet.size()-1), ":")[1]);
				i++;
				}
			//��¼���θ���ʱ��
			time = System.currentTimeMillis();
		}
		return populateSearchKey;
	}
	
	
	 public  <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
	        Map<K, V> result = new LinkedHashMap<>();
	        Stream<Entry<K, V>> st = map.entrySet().stream();

	        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));

	        return result;
	    }


}

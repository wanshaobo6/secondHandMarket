/**   
* @Title: SearchRecorderImpl.java 
* @Package com.pxxysecondhand.component.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 万少波

* @date 2019年3月3日 下午8:10:33 
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
 * @author 万少波
 *
 */
public class SearchRecorderImpl implements ISearchRecorder{
	
	@Autowired
	private JedisClient jedisClient;
	
	private List<String> populateSearchKey = new ArrayList();
	
	private long time = 0;
	
	@Value("${UPDATE_POPULATE_SEARCH_INTERVAL}")
	private long UPDATE_POPULATE_SEARCH_INTERVAL;
	
	
	//搜索关键字的用户记录存储前缀
	@Value("${SEARCH_USER_PREFIX}")
	private String SEARCH_USER_PREFIX;
	
	//搜索关键字数量存储前缀
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
		//判断KeyWords Token是否为空
		if(StringUtils.isEmpty(keyWords) || StringUtils.isEmpty(token)){
			return;
		}
		
		//避免用户刷热词
		String userSearchRecord = jedisClient.hget(SEARCH_USER_PREFIX+token,keyWords);
		 if(!StringUtils.isEmpty(userSearchRecord)) {
			 List<Long> searchTimeList = JsonUtils.jsonToList(userSearchRecord, Long.class);
			 recordList.addAll(searchTimeList);
			 if(System.currentTimeMillis()-searchTimeList.get(0) < SEARCH_RECORD_INTERVAL) {
				 return ; 
			 }
		 }
		//增加搜索数量
		String searchNumStr = jedisClient.get(SEARCH_KEYWORD_NUM_PREFIX+keyWords);
		jedisClient.set(SEARCH_KEYWORD_NUM_PREFIX+keyWords, 
				String.valueOf(StringUtils.isEmpty(searchNumStr) ? 1 :Integer.valueOf(searchNumStr)+1));
		//增加用户搜索记录
		jedisClient.hset(SEARCH_USER_PREFIX+token,keyWords,JsonUtils.objectToJson(recordList));

	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.#getPopulteKeywords(int)
	 */
	@Override
	public List<String> getPopulteKeywords() {
		// TODO Auto-generated method stub
		//如果time=0或者 超过currtime-time超过间隔  重新获得最热词语
		if(time == 0 || System.currentTimeMillis() - time > UPDATE_POPULATE_SEARCH_INTERVAL) {
			Map<String,Long> numMap = new HashMap();
			List<String> nameList = jedisClient.scan(0, SEARCH_KEYWORD_NUM_PREFIX+"*");
			if(CollectionUtils.isEmpty(nameList))
				return null; 
			nameList.forEach(name ->{ 
				long value = Long.valueOf(jedisClient.get(name)); 
				numMap.put(name, value);
				});
			//对Map排序
			Map<String, Long> linkedMap = sortByValue(numMap);
			List<String> keySet = new ArrayList(linkedMap.keySet());
			int i = 0;
			populateSearchKey = new ArrayList();
			while(i<POPULATE_SEARCH_KEY_NUM && keySet.size()>0) {
				populateSearchKey.add(StringUtils.split(keySet.remove(keySet.size()-1), ":")[1]);
				i++;
				}
			//记录本次更新时间
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

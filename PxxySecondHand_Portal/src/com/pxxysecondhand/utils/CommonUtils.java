package com.pxxysecondhand.utils;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.util.StringUtils;

public class CommonUtils {
	
  /**
   * 
  * @Title: CommonUtils.java 
  * @Package com.pxxysecondhand.utils 
  * @Description: UUID��ʽ��������
  * @author  
  * @date 2018��10��28�� ����9:55:06 
  * @version V1.0
   */
	public static String  uuidGenerator() {
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		return uuid;
	}
	/**
	 * ��õ�ǰURL��ǰ׺
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��2�� ����7:49:47 
	* @version V1.0
	 */
	public static String getURLByServletRequest(HttpServletRequest request) {
		if(request==null)
			return null;
		StringBuffer sb = request.getRequestURL();
		System.out.println("URL:"+sb.toString());
		return sb.toString();
	}
	/**
	 * ��ȡ��һ��ͼƬ
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��4�� ����8:37:06 
	* @version V1.0
	 */
	public static String getLargeImage(String itemimages) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(itemimages))
			return null;
		String[] str = itemimages.split(",");
		return str[0];
	}
	/**
	 * �Բ���ȷ�ķ�ʽα���ֲ��һ�ø�ʱ������ڵļ��
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��2�� ����7:52:04 
	* @version V1.0
	 */
	public static String getTimeIntervalInImpreciseWay(Date date) {
		boolean negativeFlag = false;
		if(date==null)
			return null;
		Date now = new Date();
		long inter = now.getTime()-date.getTime(); 
		if(inter<0) {
			negativeFlag = true;
			inter = - inter;
		}
		BigInteger interval = BigInteger.valueOf(inter);
		BigInteger mom = new BigInteger("2592000000");
		BigInteger dd = new BigInteger("86400000");
		BigInteger hh = new BigInteger("3600000");
		BigInteger ss = new BigInteger("60000");
		if(interval.compareTo(dd)<0) {
			if(interval.compareTo(hh)<0) {
				if(interval.compareTo(ss)<0) {
					//һ�����ڷ��ظո�
					if(negativeFlag) {
						return "����";
					}
					return "�ո�";
				}else {
					//1Сʱ����**����ǰ
					BigInteger minute = interval.divide(ss);
					if(negativeFlag) {
						return minute.longValue()+"�ֺ�";
					}
					return minute.longValue()+"��ǰ";
				}
			}else {
				//24Сʱ���ڷ���**Сʱǰ
				BigInteger hour = interval.divide(hh);
				if(negativeFlag) {
					return hour.longValue()+"Сʱ��";
				}
				return hour.longValue()+"Сʱǰ";
			}
		}else {
			//һ���·��ض�����ǰ
			if(interval.compareTo(mom)<0) {
				BigInteger day = interval.divide(dd);
				if(negativeFlag) {
					return day.longValue()+"���";
				}
				return day.longValue()+"��ǰ";
			}else {
				//�����·�һ�ɰ�**��ǰ����
				BigInteger month = interval.divide(mom);
				if(negativeFlag) {
					return month.longValue()+"�º�";
				}
				return month.longValue()+"��ǰ";
			}
		}
	}
	
	/**
	 * �����û����ּ����û���������
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��2�� ����9:15:53 
	* @version V1.0
	 */
	public static Integer caculateCreditStart(Integer credit) {
		return null;
	}
	
	//������ҳ����
	public static String handleTitleInMaxLength(int length, String title) {
		// TODO Auto-generated method stub
		if(title.length()<=length) 
			return title;
		return title.substring(0,length-1)+"...";
	}
	
}

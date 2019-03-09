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
  * @Description: UUID格式化生成器
  * @author  
  * @date 2018年10月28日 上午9:55:06 
  * @version V1.0
   */
	public static String  uuidGenerator() {
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		return uuid;
	}
	/**
	 * 获得当前URL的前缀
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年12月2日 下午7:49:47 
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
	 * 抽取第一张图片
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年12月4日 下午8:37:06 
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
	 * 以不精确的方式伪二分查找获得该时间和现在的间隔
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年12月2日 下午7:52:04 
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
					//一分钟内返回刚刚
					if(negativeFlag) {
						return "马上";
					}
					return "刚刚";
				}else {
					//1小时返回**分钟前
					BigInteger minute = interval.divide(ss);
					if(negativeFlag) {
						return minute.longValue()+"分后";
					}
					return minute.longValue()+"分前";
				}
			}else {
				//24小时返内返回**小时前
				BigInteger hour = interval.divide(hh);
				if(negativeFlag) {
					return hour.longValue()+"小时后";
				}
				return hour.longValue()+"小时前";
			}
		}else {
			//一个月返回多少天前
			if(interval.compareTo(mom)<0) {
				BigInteger day = interval.divide(dd);
				if(negativeFlag) {
					return day.longValue()+"天后";
				}
				return day.longValue()+"天前";
			}else {
				//超过月份一律按**月前计算
				BigInteger month = interval.divide(mom);
				if(negativeFlag) {
					return month.longValue()+"月后";
				}
				return month.longValue()+"月前";
			}
		}
	}
	
	/**
	 * 根据用户积分计算用户的信誉度
	* @Title: CommonUtils.java 
	* @Package com.pxxysecondhand.utils 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年12月2日 下午9:15:53 
	* @version V1.0
	 */
	public static Integer caculateCreditStart(Integer credit) {
		return null;
	}
	
	//处理首页标题
	public static String handleTitleInMaxLength(int length, String title) {
		// TODO Auto-generated method stub
		if(title.length()<=length) 
			return title;
		return title.substring(0,length-1)+"...";
	}
	
}

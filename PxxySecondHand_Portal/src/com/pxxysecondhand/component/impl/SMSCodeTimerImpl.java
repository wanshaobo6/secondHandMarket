package com.pxxysecondhand.component.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.pxxysecondhand.component.SMSCodeTimer;
/**
 * 单例模式保证只有一个注册器
 * xml文件注入Spring容器中
 * @author 
 *
 */
public class SMSCodeTimerImpl implements SMSCodeTimer{
	
	@Value("${SEND_SMS_CODE_INTERVAL}")
	private Integer SEND_SMS_CODE_INTERVAL;
	
	private Map<String,Long> smsCodeRegister = new HashMap();
	
	//我能获得验证码吗
	@Override
	public boolean canIGetSmsCode(String phonnenumber) {
		// TODO Auto-generated method stub
		Long date = smsCodeRegister.get(phonnenumber);
		if(date==null) 
	    	return true;
		Long now = new Date().getTime();
		long second = (now - date)/1000;
		System.out.println("间隔时间"+second);
		//时间间隔不能超过60秒
		if(second>=SEND_SMS_CODE_INTERVAL) {
			return true;
		}else {
			return false;
		}
	}
	
	//我刚刚得到了验证码
	@Override
	public void registSendSmsCode(String keyword) {
		// TODO Auto-generated method stub
		smsCodeRegister.put(keyword, new Date().getTime());
	}	
}

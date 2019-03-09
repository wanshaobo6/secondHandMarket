package com.pxxysecondhand.component.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.pxxysecondhand.component.SMSCodeTimer;
/**
 * ����ģʽ��ֻ֤��һ��ע����
 * xml�ļ�ע��Spring������
 * @author 
 *
 */
public class SMSCodeTimerImpl implements SMSCodeTimer{
	
	@Value("${SEND_SMS_CODE_INTERVAL}")
	private Integer SEND_SMS_CODE_INTERVAL;
	
	private Map<String,Long> smsCodeRegister = new HashMap();
	
	//���ܻ����֤����
	@Override
	public boolean canIGetSmsCode(String phonnenumber) {
		// TODO Auto-generated method stub
		Long date = smsCodeRegister.get(phonnenumber);
		if(date==null) 
	    	return true;
		Long now = new Date().getTime();
		long second = (now - date)/1000;
		System.out.println("���ʱ��"+second);
		//ʱ�������ܳ���60��
		if(second>=SEND_SMS_CODE_INTERVAL) {
			return true;
		}else {
			return false;
		}
	}
	
	//�Ҹոյõ�����֤��
	@Override
	public void registSendSmsCode(String keyword) {
		// TODO Auto-generated method stub
		smsCodeRegister.put(keyword, new Date().getTime());
	}	
}

package com.pxxysecondhand.utils;

import org.junit.Test;

/**
 * 6位验证码生成器
 * @author  
 *
 */
public class SMSCodeGenerator {
	
	public static String generateSmsCode() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++) {
			int a = (int)(Math.random()*10);
			sb.append(String.valueOf(a));
		}
		return sb.toString();
	}
}

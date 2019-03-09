package com.pxxysecondhand.component;

public interface SMSCodeTimer  {
	//判断是否满足发送验证码的条件
	public boolean canIGetSmsCode(String phonnenumber);
	
	//发送验证码前等登记时间
	public void registSendSmsCode(String phonenumber);
}

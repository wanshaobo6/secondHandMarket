package com.pxxysecondhand.component;

public interface SMSCodeTimer  {
	//�ж��Ƿ����㷢����֤�������
	public boolean canIGetSmsCode(String phonnenumber);
	
	//������֤��ǰ�ȵǼ�ʱ��
	public void registSendSmsCode(String phonenumber);
}

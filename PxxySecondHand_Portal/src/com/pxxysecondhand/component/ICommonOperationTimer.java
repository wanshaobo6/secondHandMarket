package com.pxxysecondhand.component;


public interface ICommonOperationTimer  {
	//�ж��Ƿ����㷢����֤�������
	public boolean canIDoAgain(String name ,String thing , long timeSecondInterva);
	
	//�Ҹ����������
	public void IHaveDoSomeThing(String name,String thing);
}

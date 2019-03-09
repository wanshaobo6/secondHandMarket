package com.pxxysecondhand.component;


public interface ICommonOperationTimer  {
	//判断是否满足发送验证码的条件
	public boolean canIDoAgain(String name ,String thing , long timeSecondInterva);
	
	//我刚做了这件事
	public void IHaveDoSomeThing(String name,String thing);
}

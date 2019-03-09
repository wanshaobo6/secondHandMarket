/**   
* @Title: ITradeService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��5�� ����8:21:31 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.Trade;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.tempPojo.MyCollections;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.MyPurchase;
import com.pxxysecondhand.tempPojo.MySelled;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public interface ITradeService {
	
	//ͨ��tradeId���trade����
	Trade getTradeByTradeId(String TradeId);
	
	//�����û���������
	void reaccessSellerCreditLevel(String userId);
	
	//���ͽ���ȷ����֤��
	CommonResult sendTradeSmsMessage(String userId,String phonenumber,String itemId);
	
	//��������
	CommonResult makeADeal(User user, Item item, String smsCode);
	
	//��֤��֤�����ȷ��
	boolean validateMakeADealSMSCode(User user, String itemId, String smsCode);
	
	//��������еĽ����б�
	public SearchResult<MyPublic> getAllOfMyTrade(HttpServletRequest request,String userId,int page,int rows);
	
	//ȷ���ջ�״̬
	public CommonResult confirmMyTrade(HttpServletRequest request,User user , String tradeId);

	//�������
	CommonResult evaluateItem(HttpServletRequest request, User user, Integer evaluateLevel, String content , String tradeId);
	
	//n�첻�����Զ�ȷ�ϣ�����״̬�Զ��ӽ����е�ȷ���ջ�
	int autoChangeTradingToTraded(String tradeId);

	//n�첻����ϵͳ�Զ���ȷ���ջ�״̬���������״̬
	int autoChangeTradedToComplish(String tradeId);
	
	//ȡ������
	CommonResult cancelTheTrade(User user, String tradeId);

	//���ݶ����Ų�ѯ
	MyPurchase showCommentByTradeId(User user, String tradeId);

	//��鵱ǰ�û������ ��ҷ���1 ���ҷ���2  ���෵��0
	int checkMyIdentity(User user,String tradeId);
	
	//����idαɾ�����׼�¼
	CommonResult fadeDeleteTradeRecord(String tradeId, int i);

	//�ж϶�ú��Զ��ջ� Ĭ������
	String caculateTimeLeftMessage(Date createTime,Date complishTime,Integer status);

	//���������������
	SearchResult<MySelled> getAllOfMySelled(HttpServletRequest request, User user, int page, int rows);
	
}

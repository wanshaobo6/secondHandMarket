/**   
* @Title: ITradeService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月5日 上午8:21:31 
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
	
	//通过tradeId获得trade对象
	Trade getTradeByTradeId(String TradeId);
	
	//评估用户的信誉度
	void reaccessSellerCreditLevel(String userId);
	
	//发送交易确认验证码
	CommonResult sendTradeSmsMessage(String userId,String phonenumber,String itemId);
	
	//新增交易
	CommonResult makeADeal(User user, Item item, String smsCode);
	
	//验证验证码的正确性
	boolean validateMakeADealSMSCode(User user, String itemId, String smsCode);
	
	//获得我所有的交易列表
	public SearchResult<MyPublic> getAllOfMyTrade(HttpServletRequest request,String userId,int page,int rows);
	
	//确认收货状态
	public CommonResult confirmMyTrade(HttpServletRequest request,User user , String tradeId);

	//添加评价
	CommonResult evaluateItem(HttpServletRequest request, User user, Integer evaluateLevel, String content , String tradeId);
	
	//n天不操作自动确认，交易状态自动从交易中到确认收货
	int autoChangeTradingToTraded(String tradeId);

	//n天不操作系统自动从确认收货状态到交易完成状态
	int autoChangeTradedToComplish(String tradeId);
	
	//取消交易
	CommonResult cancelTheTrade(User user, String tradeId);

	//根据订单号查询
	MyPurchase showCommentByTradeId(User user, String tradeId);

	//检查当前用户的身份 买家返回1 卖家返回2  其余返回0
	int checkMyIdentity(User user,String tradeId);
	
	//根据id伪删除交易记录
	CommonResult fadeDeleteTradeRecord(String tradeId, int i);

	//判断多久后自动收货 默认评价
	String caculateTimeLeftMessage(Date createTime,Date complishTime,Integer status);

	//获得我所有卖出的
	SearchResult<MySelled> getAllOfMySelled(HttpServletRequest request, User user, int page, int rows);
	
}

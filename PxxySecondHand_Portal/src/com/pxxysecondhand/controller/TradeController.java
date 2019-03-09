/**   
* @Title: TradwController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��5�� ����8:12:41 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.service.ITradeService;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.MyPurchase;
import com.pxxysecondhand.tempPojo.MySelled;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.threads.CraftsMan1;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/trade")
public class TradeController {
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private ITradeService tradeService;
	
	@Autowired
	private IItemService itemService;
	/**
	 * ���ͽ���ȷ����֤��
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��5�� ����9:20:40 
	* @version V1.0
	 */
	@RequestMapping(value="/sendTradeConfirmCode",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult sendTradeConfirmCode(HttpServletRequest request,@RequestParam(required=true)String itemId) {
		User user = commentService.checkIsLogin(request);
		//2.�ж����õ�״̬
		Item item = itemService.getItemByItemId(itemId);
		if(item==null || item.getIsintrade()!=0) {
			return  CommonResult.build(500, "�ñ��������ڻ����Ѿ��¼�");
		}
		//3.���ͽ���ȷ����֤��
		CommonResult result = tradeService.sendTradeSmsMessage(user.getId(),user.getPhonenumber(),itemId);
		return result;
	}
	
	/**
	 * ��������
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��5�� ����9:20:58 
	* @version V1.0
	 */
	@RequestMapping(value="/makeADeal",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult makeADeal(HttpServletRequest request,@RequestParam(required=true)String itemId,@RequestParam(required=true)String smsCode) {
		User user = commentService.checkIsLogin(request);
		//2.�ж����õ�״̬
		Item item = itemService.getItemByItemId(itemId);
		if(item==null || item.getIsintrade()!=0) {
			return  CommonResult.build(500, "�ñ��������ڻ����Ѿ��¼�");
		}
		//3.���ܺ��Լ�������
		if(user.getId().equals(item.getUserid())) {
			return  CommonResult.build(500, "���ܺ��Լ�������");
		}
		//4.��������
		CommonResult result = tradeService.makeADeal(user,item,smsCode);
		return result;
	}
	
	/**
	 * ��ʾ�������򵽵�
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��5�� ����7:39:16 
	* @version V1.0
	 */
	@RequestMapping("/getAllOfMyTrade")
	public ModelAndView getAllOfMyTrade(HttpServletRequest request,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="6")int rows) {
		ModelAndView mv = new ModelAndView();
		User user = commentService.checkIsLogin(request);
		//��ѯ�����������û��Ķ���
		SearchResult<MyPublic> result = tradeService.getAllOfMyTrade(request,user.getId(), page, rows);
		mv.setViewName("myPerchase");
		mv.addObject("data",result);
		return mv;
	}
	
	/**
	 * ȷ�Ͻ���
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��6�� ����5:09:10 
	* @version V1.0
	 */
	@RequestMapping(value="/confirmMyTrade",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult confirmMyTrade(HttpServletRequest request,@RequestParam(required=true)String tradeId) {
		User user = commentService.checkIsLogin(request);
		CommonResult result = tradeService.confirmMyTrade(request, user, tradeId);
		return result;
	}
	
	/**
	 * ��������
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��6�� ����5:14:08 
	* @version V1.0
	 */
	@RequestMapping(value="/evaluateItem",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult evaluateItem(HttpServletRequest request,@RequestParam(required=true)Integer evaluateLevel,
			@RequestParam(required=true)String content,@RequestParam(required=true)String tradeId) {
		User user = commentService.checkIsLogin(request);
		CommonResult result = tradeService.evaluateItem(request, user, evaluateLevel , content ,tradeId);
		return result;
	}
	
	/**
	 * ȡ����������
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��6�� ����5:15:08 
	* @version V1.0
	 */
	@RequestMapping(value="/cancelTheTrade",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult cancelTheTrade(HttpServletRequest request,@RequestParam(required=true)String tradeId) {
		User user = commentService.checkIsLogin(request);
		CommonResult result = tradeService.cancelTheTrade(user,tradeId);
		return result;
	}
	
	/**
	 *�鿴��������
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��11�� ����6:12:06 
	* @version V1.0
	 */
	@RequestMapping(value="/showCommentByTradeId",method=RequestMethod.GET)
	public ModelAndView showCommentByTradeId(HttpServletRequest request,@RequestParam(required=true)String tradeId) {
		ModelAndView mv = new ModelAndView();
		User user = commentService.checkIsLogin(request);
		MyPurchase myPurchase = tradeService.showCommentByTradeId(user,tradeId);
		if(myPurchase==null) {
			mv.setViewName("error");
		}else {
			mv.addObject("data",myPurchase);
		    mv.setViewName("comment");
		} 
		return mv;
	}
	/**
	 * ɾ��������Ϣ
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��12�� ����8:54:07 
	* @version V1.0
	 */
	@RequestMapping(value="/deleteTradeRecord",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult deleteBuyerTrade(HttpServletRequest request,@RequestParam(required=true)String tradeId) {
		//��ȡ���
		User user = commentService.checkIsLogin(request);
		//�ж�����һ�������
		int i = tradeService.checkMyIdentity(user,tradeId);
		if(i==0) {
			return CommonResult.build(500, "�Ƿ�����");
		}
		CommonResult result = tradeService.fadeDeleteTradeRecord(tradeId,i);
		return result;
	}
	/**
	 * ��������е�������¼
	* @Title: TradeController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��18�� ����1:31:22 
	* @version V1.0
	 */
	@RequestMapping(value="/getAllOfMySelled",method=RequestMethod.GET)
	public ModelAndView getAllOfMySelled(HttpServletRequest request , @RequestParam(defaultValue="1") int page ,@RequestParam(defaultValue="6") int rows) {
		ModelAndView mv = new ModelAndView();
		User user = commentService.checkIsLogin(request);
		//��ѯ�����������û��Ķ���
		SearchResult<MySelled> result = tradeService.getAllOfMySelled(request,user, page, rows);
		mv.setViewName("mySelled");
		mv.addObject("data",result);
		return mv;
	}
	
}

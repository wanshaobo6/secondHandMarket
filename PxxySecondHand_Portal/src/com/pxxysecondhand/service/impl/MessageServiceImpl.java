/**   
* @Title: MessageServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月19日 下午7:29:45 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxysecondhand.mapper.MessageMapper;
import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.Message;
import com.pxxysecondhand.pojo.MessageExample;
import com.pxxysecondhand.pojo.MessageExample.Criteria;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.IMessageService;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.tempPojo.TempMessage;
import com.pxxysecondhand.tempPojo.WebDynamicMessage;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;

/**
 * @author  
 *
 */
@Service
public class MessageServiceImpl implements IMessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	//首页消息标题最大长度
	@Value("${INDEX_LONGEST_MESSAGE_TITLE_NUM}")
	private int INDEX_LONGEST_MESSAGE_TITLE_NUM;
	
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IMessageServcie#getGuestWebMessage()
	 */
	@Override
	public SearchResult<WebDynamicMessage> getWebMessageAutomatically(String userId , int page , int size) {
		// TODO Auto-generated method stub
		//查询数据
		SearchResult<WebDynamicMessage> result = new SearchResult();
		PageHelper.startPage(page, size);
		MessageExample example = new MessageExample();
		example.setOrderByClause("'createTime' DESC");
		//条件1
		Criteria criteria = example.createCriteria();
		criteria.andTooneEqualTo(0);
			//用户登录情况
			if(userId!=null){
				//条件2
				Criteria criteria2 = example.createCriteria();
				criteria2.andTooneEqualTo(1);
				criteria2.andUseridEqualTo(userId);
				//或连接
				example.or(criteria2);
			}
		List<Message> list = messageMapper.selectByExample(example);
		//填充数据
		PageInfo<Message> pageInfo = new PageInfo(list);
		int totalcount = (int) pageInfo.getTotal();
		result.setTotalCount(totalcount);
		result.setCurrentPage(page);
		int totalPage = totalcount%size==0?totalcount/size:(totalcount/size+1);
		result.setTotalPage(totalPage);
		result.setItemList(messageListToWebDynamicMessageList(list));
		return result;
	}

	//
	private List<WebDynamicMessage> messageListToWebDynamicMessageList(List<Message> list) {
		// TODO Auto-generated method stub
		if(list==null || list.size()==0)
			return null;
		List<WebDynamicMessage> tempList = new ArrayList();
		for (Message message : list) {
			WebDynamicMessage webDynamicMessage = new WebDynamicMessage();
			webDynamicMessage.setId(message.getId());
			webDynamicMessage.setToone(message.getToone());
			webDynamicMessage.setIsRead(message.getIsread());
			webDynamicMessage.setTitle(CommonUtils.handleTitleInMaxLength(INDEX_LONGEST_MESSAGE_TITLE_NUM,message.getTitle()));
			webDynamicMessage.setCreateTimeImPrecise(CommonUtils.getTimeIntervalInImpreciseWay(message.getCreatetime()));
			tempList.add(webDynamicMessage);
		}
		return tempList;
	}




	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IMessageService#getMessageById(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public TempMessage getMessageById(User user, String messageId) throws Exception{
		// TODO Auto-generated method stub
		//查看当前消息
		Message message = messageMapper.selectByPrimaryKey(messageId);
		//该消息不存在
		if(message==null )
			throw new Exception("消息不存在");
		//消息群发
		TempMessage tempMessage = transformMessageToTempMessage(message);
		if(message.getToone() == 0)
			return tempMessage;
		if(message.getToone() == 1 && user!= null && user.getId().equals(message.getUserid())){
			//消息单发
			return tempMessage;
		}
		throw new Exception("用户权限不足");
	}

	private TempMessage transformMessageToTempMessage(Message message) {
		// TODO Auto-generated method stub
		TempMessage tempMessage = new TempMessage();
		tempMessage.setId(message.getId());
		tempMessage.setTitle(message.getTitle());
		tempMessage.setToone(message.getToone());
		tempMessage.setUserId(message.getUserid());
		//tempMessage.setUserName(message.getU);
		String str;
		try {
			str = new String(message.getContent(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			str="转化出错";
		}
		tempMessage.setContent(str);
		tempMessage.setCreatetime(message.getCreatetime());
		return tempMessage;
	}
 
}

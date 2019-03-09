/**   
* @Title: MessageServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��19�� ����7:29:45 
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
	
	//��ҳ��Ϣ������󳤶�
	@Value("${INDEX_LONGEST_MESSAGE_TITLE_NUM}")
	private int INDEX_LONGEST_MESSAGE_TITLE_NUM;
	
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IMessageServcie#getGuestWebMessage()
	 */
	@Override
	public SearchResult<WebDynamicMessage> getWebMessageAutomatically(String userId , int page , int size) {
		// TODO Auto-generated method stub
		//��ѯ����
		SearchResult<WebDynamicMessage> result = new SearchResult();
		PageHelper.startPage(page, size);
		MessageExample example = new MessageExample();
		example.setOrderByClause("'createTime' DESC");
		//����1
		Criteria criteria = example.createCriteria();
		criteria.andTooneEqualTo(0);
			//�û���¼���
			if(userId!=null){
				//����2
				Criteria criteria2 = example.createCriteria();
				criteria2.andTooneEqualTo(1);
				criteria2.andUseridEqualTo(userId);
				//������
				example.or(criteria2);
			}
		List<Message> list = messageMapper.selectByExample(example);
		//�������
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
		//�鿴��ǰ��Ϣ
		Message message = messageMapper.selectByPrimaryKey(messageId);
		//����Ϣ������
		if(message==null )
			throw new Exception("��Ϣ������");
		//��ϢȺ��
		TempMessage tempMessage = transformMessageToTempMessage(message);
		if(message.getToone() == 0)
			return tempMessage;
		if(message.getToone() == 1 && user!= null && user.getId().equals(message.getUserid())){
			//��Ϣ����
			return tempMessage;
		}
		throw new Exception("�û�Ȩ�޲���");
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
			str="ת������";
		}
		tempMessage.setContent(str);
		tempMessage.setCreatetime(message.getCreatetime());
		return tempMessage;
	}
 
}

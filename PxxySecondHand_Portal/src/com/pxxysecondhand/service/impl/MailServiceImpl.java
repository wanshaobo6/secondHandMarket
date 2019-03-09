/**   
* @Title: MailServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��19�� ����3:59:09 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pxxysecondhand.component.ICommonOperationTimer;
import com.pxxysecondhand.mapper.MailboxMapper;
import com.pxxysecondhand.pojo.Mailbox;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.IMailService;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;
import com.pxxysecondhand.utils.MailUtils;

/**
 * @author  
 *
 */
@Service
public class MailServiceImpl implements IMailService {
	@Autowired
	private ICommonOperationTimer comonOperationTimer;
	
	@Autowired
	private MailboxMapper mailboxMapper;
	
	@Autowired
	private MailUtils mailUtils;
	
	//#������Ϣ����
	@Value("${WEB_MASTER_MAIL_ADDR}")
	private String WEB_MASTER_MAIL_ADDR;
	
	//#�����ʼ���վ���¼�����
	@Value("${SEND_TO_WEB_MASTER_EVENT_NAME}")
	private String SEND_TO_WEB_MASTER_EVENT_NAME;
	
	//#��վ��������Ϣʱ����
	@Value("${NEXT_SEND_MAIL_TO_MASTER_SEC_INTERVAL}")
	private long NEXT_SEND_MAIL_TO_MASTER_SEC_INTERVAL;
	
	//#��վ��������Ϣ������
	@Value("${SEND_MAIL_TO_MASTER_SUBJECT}")
	private String SEND_MAIL_TO_MASTER_SUBJECT;
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IMailService#sendMailToWebMaster(com.pxxysecondhand.pojo.User)
	 */
	@Override
	public CommonResult sendMailToWebMaster(User user,String msg) {
		// TODO Auto-generated method stub
		//����Ƶ������  
		if(!comonOperationTimer.canIDoAgain(user.getId(), SEND_TO_WEB_MASTER_EVENT_NAME, NEXT_SEND_MAIL_TO_MASTER_SEC_INTERVAL)) {
			return CommonResult.build(500, "����̫Ƶ��");
		}
		//�����ʼ�
		boolean b = mailUtils.sendEmail(WEB_MASTER_MAIL_ADDR, user, msg, SEND_MAIL_TO_MASTER_SUBJECT);
		// ������Ϣ
		Mailbox mailbox = new Mailbox();
		mailbox.setIssuccess(1);
		if(!b) {
		  mailbox.setIssuccess(0);
		}
		mailbox.setId(CommonUtils.uuidGenerator());
		mailbox.setUserid(user.getId());
		mailbox.setSenddate(new Date());
		mailbox.setContent(msg);
		mailboxMapper.insert(mailbox);
		//��¼�¼�
		comonOperationTimer.IHaveDoSomeThing(user.getId(), SEND_TO_WEB_MASTER_EVENT_NAME);
		if(!b) {
			return CommonResult.build(500, "����ʧ��");
		}
		return CommonResult.ok();
	}

}

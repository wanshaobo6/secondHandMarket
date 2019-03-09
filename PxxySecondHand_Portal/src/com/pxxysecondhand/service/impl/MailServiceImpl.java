/**   
* @Title: MailServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月19日 下午3:59:09 
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
	
	//#接收消息邮箱
	@Value("${WEB_MASTER_MAIL_ADDR}")
	private String WEB_MASTER_MAIL_ADDR;
	
	//#发送邮件给站长事件名称
	@Value("${SEND_TO_WEB_MASTER_EVENT_NAME}")
	private String SEND_TO_WEB_MASTER_EVENT_NAME;
	
	//#给站长发送消息时间间隔
	@Value("${NEXT_SEND_MAIL_TO_MASTER_SEC_INTERVAL}")
	private long NEXT_SEND_MAIL_TO_MASTER_SEC_INTERVAL;
	
	//#给站长发送消息的主题
	@Value("${SEND_MAIL_TO_MASTER_SUBJECT}")
	private String SEND_MAIL_TO_MASTER_SUBJECT;
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IMailService#sendMailToWebMaster(com.pxxysecondhand.pojo.User)
	 */
	@Override
	public CommonResult sendMailToWebMaster(User user,String msg) {
		// TODO Auto-generated method stub
		//不能频繁发送  
		if(!comonOperationTimer.canIDoAgain(user.getId(), SEND_TO_WEB_MASTER_EVENT_NAME, NEXT_SEND_MAIL_TO_MASTER_SEC_INTERVAL)) {
			return CommonResult.build(500, "操作太频繁");
		}
		//发送邮件
		boolean b = mailUtils.sendEmail(WEB_MASTER_MAIL_ADDR, user, msg, SEND_MAIL_TO_MASTER_SUBJECT);
		// 保存信息
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
		//记录事件
		comonOperationTimer.IHaveDoSomeThing(user.getId(), SEND_TO_WEB_MASTER_EVENT_NAME);
		if(!b) {
			return CommonResult.build(500, "发送失败");
		}
		return CommonResult.ok();
	}

}

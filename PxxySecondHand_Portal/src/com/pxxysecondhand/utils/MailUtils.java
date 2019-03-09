/**   
* @Title: MailUtils.java 
* @Package com.pxxysecondhand.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月3日 下午7:09:54 
* @version V1.0   
*/
package com.pxxysecondhand.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pxxysecondhand.pojo.User;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

/**
 * @author  
 * Spring容器管理  XMl文件注入
 */
public class MailUtils {
	
	//邮件发送方邮箱
	@Value("${AUTHENTICATION_NAME}")
	private String AUTHENTICATION_NAME; 
	
	//邮件发送方密码
	@Value("${AUTHENTICATION_PASSWORD}")
	private String AUTHENTICATION_PASSWORD; 
	
	//发送邮件 
	public  boolean sendEmail(String emailaddress,User user, String msg , String subject) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.163.com");
			email.setCharset("UTF-8");
			email.addTo(emailaddress);// 收件地址
			email.setFrom(AUTHENTICATION_NAME, "萍院跳蚤市场");
			email.setAuthentication(AUTHENTICATION_NAME, AUTHENTICATION_PASSWORD);
			email.setSubject(subject);
			email.setMsg("发送者姓名:"+user.getUsername()+",<br>"+"发送者联系方式:"+user.getPhonenumber()+",<br>"+"发送的消息:"+msg);
			email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
   }
	
	//接收未读邮件
	public  Message[] receiveEmail() throws Exception{
	      String host = "imap.163.com";
        String username = AUTHENTICATION_NAME;
        String password = AUTHENTICATION_PASSWORD;
 
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
 
        Store store = session.getStore("imap");
        store.connect(host, username, password);
 
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        int nuReadMessageNum = folder.getUnreadMessageCount();
        System.out.println(nuReadMessageNum+"封未读邮件");
        if(nuReadMessageNum == 0)
        	return null;
        else
        	return  folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());
        
	}
}















/*

String imapserver = "imap.163.com"; // 邮件服务器
String user = AUTHENTICATION_NAME;
String password = AUTHENTICATION_PASSWORD;     // 根据自已的密码修改
// 获取默认会话
Properties prop = System.getProperties();
prop.put("mail.imap.host",imapserver);

prop.put("mail.imap.auth.plain.disable","true");
Session mailsession=Session.getInstance(prop,null);
mailsession.setDebug(false); //是否启用debug模式
IMAPFolder folder= null;
IMAPStore store=null;
int total= 0;
try{
   store=(IMAPStore)mailsession.getStore("imap");  // 使用imap会话机制，连接服务器
   store.connect(imapserver,user,password);
   folder=(IMAPFolder)store.getFolder("INBOX"); //获得收件箱 
   // 使用读写方式打开收件箱 
   folder.open(Folder.READ_WRITE);
   //获取总邮件数
   total = folder.getMessageCount();
   System.out.println("-----------------您的邮箱共有邮件：" + total+" 封--------------");
   // 得到收件箱文件夹信息，获取邮件列表
   Message[] msgs =folder.getMessages();
   for (Message message : msgs) {
	   Multipart content = (Multipart)message.getContent();
	  System.out.println( content.getBodyPart(0).getContent());
	  System.out.println(message.getHeader("Status") );
      // 删除邮件    
      // message.setFlag(Flags.Flag.DELETED, true);    
      // 标记为已读    
      // message.setFlag(Flags.Flag.SEEN, true);    
   }
   System.out.println("\t收件箱的总邮件数：" + msgs.length);  
   System.out.println("\t未读邮件数：" + folder.getUnreadMessageCount());  
   System.out.println("\t新邮件数：" + folder.getNewMessageCount());  
   System.out.println("----------------End------------------");
}
catch(MessagingException ex){
     System.err.println("不能以读写方式打开邮箱!");
     ex.printStackTrace();
}finally {
// 释放资源
  try{
      if(folder!=null)
          folder.close(true); //退出收件箱时,删除做了删除标识的邮件
     if (store != null)
        store.close();
    }catch(Exception bs){
     bs.printStackTrace();
    }             
}          
*/
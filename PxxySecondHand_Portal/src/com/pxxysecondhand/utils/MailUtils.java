/**   
* @Title: MailUtils.java 
* @Package com.pxxysecondhand.utils 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��3�� ����7:09:54 
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
 * Spring��������  XMl�ļ�ע��
 */
public class MailUtils {
	
	//�ʼ����ͷ�����
	@Value("${AUTHENTICATION_NAME}")
	private String AUTHENTICATION_NAME; 
	
	//�ʼ����ͷ�����
	@Value("${AUTHENTICATION_PASSWORD}")
	private String AUTHENTICATION_PASSWORD; 
	
	//�����ʼ� 
	public  boolean sendEmail(String emailaddress,User user, String msg , String subject) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.163.com");
			email.setCharset("UTF-8");
			email.addTo(emailaddress);// �ռ���ַ
			email.setFrom(AUTHENTICATION_NAME, "ƼԺ�����г�");
			email.setAuthentication(AUTHENTICATION_NAME, AUTHENTICATION_PASSWORD);
			email.setSubject(subject);
			email.setMsg("����������:"+user.getUsername()+",<br>"+"��������ϵ��ʽ:"+user.getPhonenumber()+",<br>"+"���͵���Ϣ:"+msg);
			email.send();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
   }
	
	//����δ���ʼ�
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
        System.out.println(nuReadMessageNum+"��δ���ʼ�");
        if(nuReadMessageNum == 0)
        	return null;
        else
        	return  folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());
        
	}
}















/*

String imapserver = "imap.163.com"; // �ʼ�������
String user = AUTHENTICATION_NAME;
String password = AUTHENTICATION_PASSWORD;     // �������ѵ������޸�
// ��ȡĬ�ϻỰ
Properties prop = System.getProperties();
prop.put("mail.imap.host",imapserver);

prop.put("mail.imap.auth.plain.disable","true");
Session mailsession=Session.getInstance(prop,null);
mailsession.setDebug(false); //�Ƿ�����debugģʽ
IMAPFolder folder= null;
IMAPStore store=null;
int total= 0;
try{
   store=(IMAPStore)mailsession.getStore("imap");  // ʹ��imap�Ự���ƣ����ӷ�����
   store.connect(imapserver,user,password);
   folder=(IMAPFolder)store.getFolder("INBOX"); //����ռ��� 
   // ʹ�ö�д��ʽ���ռ��� 
   folder.open(Folder.READ_WRITE);
   //��ȡ���ʼ���
   total = folder.getMessageCount();
   System.out.println("-----------------�������乲���ʼ���" + total+" ��--------------");
   // �õ��ռ����ļ�����Ϣ����ȡ�ʼ��б�
   Message[] msgs =folder.getMessages();
   for (Message message : msgs) {
	   Multipart content = (Multipart)message.getContent();
	  System.out.println( content.getBodyPart(0).getContent());
	  System.out.println(message.getHeader("Status") );
      // ɾ���ʼ�    
      // message.setFlag(Flags.Flag.DELETED, true);    
      // ���Ϊ�Ѷ�    
      // message.setFlag(Flags.Flag.SEEN, true);    
   }
   System.out.println("\t�ռ�������ʼ�����" + msgs.length);  
   System.out.println("\tδ���ʼ�����" + folder.getUnreadMessageCount());  
   System.out.println("\t���ʼ�����" + folder.getNewMessageCount());  
   System.out.println("----------------End------------------");
}
catch(MessagingException ex){
     System.err.println("�����Զ�д��ʽ������!");
     ex.printStackTrace();
}finally {
// �ͷ���Դ
  try{
      if(folder!=null)
          folder.close(true); //�˳��ռ���ʱ,ɾ������ɾ����ʶ���ʼ�
     if (store != null)
        store.close();
    }catch(Exception bs){
     bs.printStackTrace();
    }             
}          
*/
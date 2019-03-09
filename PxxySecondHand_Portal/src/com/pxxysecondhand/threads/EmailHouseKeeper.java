/**   
* @Title: EmailHouseKeeper.java 
* @Package com.pxxysecondhand.threads 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��20�� ����5:28:11 
* @version V1.0   
*/
package  com.pxxysecondhand.threads;
import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pxxysecondhand.tempPojo.WebMasterCommand;
import com.pxxysecondhand.utils.MailUtils;
import com.pxxysecondhand.utils.ShowMail;

/**
 * @author 
 * ����ִ����  //��XML��ʽ����Spring����  ������  ��ѭ��
 *
 */
public class EmailHouseKeeper implements Runnable{
	
	private Thread thread ;
	
	//�������
	private Queue<WebMasterCommand> webMasterCommands = new LinkedList();
	
	//վ������
	@Value("${WEB_MASTER_MAIL_ADDR}")
	private String WEB_MASTER_MAIL_ADDR;
	
	//����ܼ�ÿ������鿴����������ʱ�� ms
	@Value("${HOUSEKEPPER_REQUIRE_FOR_COMMAND_INTERVAL}")
	private long HOUSEKEPPER_REQUIRE_FOR_COMMAND_INTERVAL;
	
	@Autowired
	private MailUtils mailUtils;
	
	public EmailHouseKeeper() {
		//thread = new Thread(this);
		//thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
		try {
			  getHosterCommandToQueue();
			  thread.sleep(HOUSEKEPPER_REQUIRE_FOR_COMMAND_INTERVAL);
			} catch (Exception e) {
					//�����쳣��˯һ��
					 try {
						thread.sleep(HOUSEKEPPER_REQUIRE_FOR_COMMAND_INTERVAL);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
		
	}

	//ȡָ
	private void getHosterCommandToQueue() throws Exception {
		// TODO Auto-generated method stub
		Message[] messages = mailUtils.receiveEmail();
		//��δ���ʼ� 
		if(messages==null)
		    throw new Exception();
		//��ѯ��վ��������Ϣ���Ҹ�ʽ��ȷ ����weMasterQueue
	    addWebMasterCommandToQueue(messages);
	}

	//���վ������Ϣ��queue �����渽��
	private void addWebMasterCommandToQueue(Message[] messages) throws Exception {
		// TODO Auto-generated method stub
		ShowMail re ;
		for (Message message2 : messages) {
			  re = new ShowMail((MimeMessage)message2);
			  String allname = re.getFrom();
			  String hostAddress = allname.substring(allname.lastIndexOf("<")+1, allname.length()-1);
			  System.out.println("�յ��ʼ�����"+hostAddress);
			  //����վ����������Ϣ����
			  if(!hostAddress.equals(WEB_MASTER_MAIL_ADDR))
				  break;
			 boolean isSuccess =  parseWebMasterCommand(message2,re);
			 if(isSuccess)
				 downloadAttachment(message2);
		}
		
	}

	//����վ������
	private boolean parseWebMasterCommand(Message message2, ShowMail re) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	//���渽��
	private void downloadAttachment(Message message2) {
		// TODO Auto-generated method stub
		
	}

	

}

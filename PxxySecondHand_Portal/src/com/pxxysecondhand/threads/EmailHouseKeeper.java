/**   
* @Title: EmailHouseKeeper.java 
* @Package com.pxxysecondhand.threads 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月20日 下午5:28:11 
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
 * 邮箱执行器  //已XML格式加入Spring容器  自启动  死循环
 *
 */
public class EmailHouseKeeper implements Runnable{
	
	private Thread thread ;
	
	//命令队列
	private Queue<WebMasterCommand> webMasterCommands = new LinkedList();
	
	//站长邮箱
	@Value("${WEB_MASTER_MAIL_ADDR}")
	private String WEB_MASTER_MAIL_ADDR;
	
	//邮箱管家每次请求查看主人命令间隔时间 ms
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
					//出了异常再睡一下
					 try {
						thread.sleep(HOUSEKEPPER_REQUIRE_FOR_COMMAND_INTERVAL);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
		
	}

	//取指
	private void getHosterCommandToQueue() throws Exception {
		// TODO Auto-generated method stub
		Message[] messages = mailUtils.receiveEmail();
		//无未读邮件 
		if(messages==null)
		    throw new Exception();
		//查询是站长发来信息而且格式正确 加入weMasterQueue
	    addWebMasterCommandToQueue(messages);
	}

	//添加站长的消息到queue 并保存附件
	private void addWebMasterCommandToQueue(Message[] messages) throws Exception {
		// TODO Auto-generated method stub
		ShowMail re ;
		for (Message message2 : messages) {
			  re = new ShowMail((MimeMessage)message2);
			  String allname = re.getFrom();
			  String hostAddress = allname.substring(allname.lastIndexOf("<")+1, allname.length()-1);
			  System.out.println("收到邮件来自"+hostAddress);
			  //不是站长发来的信息跳过
			  if(!hostAddress.equals(WEB_MASTER_MAIL_ADDR))
				  break;
			 boolean isSuccess =  parseWebMasterCommand(message2,re);
			 if(isSuccess)
				 downloadAttachment(message2);
		}
		
	}

	//解析站长命令
	private boolean parseWebMasterCommand(Message message2, ShowMail re) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	//保存附件
	private void downloadAttachment(Message message2) {
		// TODO Auto-generated method stub
		
	}

	

}

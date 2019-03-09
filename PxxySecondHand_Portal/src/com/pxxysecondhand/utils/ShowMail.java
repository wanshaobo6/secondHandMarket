package com.pxxysecondhand.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
 
/**
 * @author yh
 * 
 */
public class ShowMail {
 
    private MimeMessage mimeMessage = null;
    private String saveAttachPath = ""; // 附件下载后的存放目录
    private StringBuffer bodyText = new StringBuffer(); // 存放邮件内容的StringBuffer对象
    private String dateFormat = "yy-MM-dd HH:mm"; // 默认的日前显示格式
 
    /**
     * 构造函数,初始化一个MimeMessage对象
     */
    public ShowMail() {
    }
 
    public ShowMail(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
        System.out.println("创建一个ReceiveEmail对象....");
    }
 
    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
        System.out.println("设置一个MimeMessage对象...");
    }
 
    /**
     * 　*　获得发件人的地址和姓名 　
     */
    public String getFrom() throws Exception {
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
        String from = address[0].getAddress();
        if (from == null) {
            from = "";
            System.out.println("无法知道发送者.");
        }
        String personal = address[0].getPersonal();
 
        if (personal == null) {
            personal = "";
            System.out.println("无法知道发送者的姓名.");
        }
 
        String fromAddr = null;
        if (personal != null || from != null) {
            fromAddr = personal + "<" + from + ">";
            System.out.println("发送者是：" + fromAddr);
        } else {
            System.out.println("无法获得发送者信息.");
        }
        return fromAddr;
    }
 
    /**
     * 　*　获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同
     * 　*　"to"----收件人　"cc"---抄送人地址　"bcc"---密送人地址 　
     */
    public String getMailAddress(String type) throws Exception {
        String mailAddr = "";
        String addType = type.toUpperCase();
 
        InternetAddress[] address = null;
        if (addType.equals("TO") || addType.equals("CC")
                || addType.equals("BCC")) {
 
            if (addType.equals("TO")) {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.TO);
            } else if (addType.equals("CC")) {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.CC);
            } else {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.BCC);
            }
 
            if (address != null) {
                for (int i = 0; i < address.length; i++) {
                    String emailAddr = address[i].getAddress();
                    if (emailAddr == null) {
                        emailAddr = "";
                    } else {
                        System.out.println("转换之前的mailAddr: " + emailAddr);
                        emailAddr = MimeUtility.decodeText(emailAddr);
                        System.out.println("转换之后的mailAddr: " + emailAddr);
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null) {
                        personal = "";
                    } else {
                        System.out.println("转换之前的personal: " + personal);
                        personal = MimeUtility.decodeText(personal);
                        System.out.println("转换之后的personal: " + personal);
                    }
                    String compositeto = personal + "<" + emailAddr + ">";
                    System.out.println("完整的邮件地址：" + compositeto);
                    mailAddr += "," + compositeto;
                }
                mailAddr = mailAddr.substring(1);
            }
        } else {
            throw new Exception("错误的电子邮件类型!");
        }
        return mailAddr;
    }
 
    /**
     * 　*　获得邮件主题 　
     */
    public String getSubject() throws MessagingException {
        String subject = "";
        try {
            System.out.println("转换前的subject:" + mimeMessage.getSubject());
            subject = MimeUtility.decodeText(mimeMessage.getSubject());
            System.out.println("转换后的subject: " + mimeMessage.getSubject());
            if (subject == null) {
                subject = "";
            }
        } catch (Exception exce) {
            exce.printStackTrace();
        }
        return subject;
    }
 
    /**
     * 　*　获得邮件发送日期 　
     */
    public String getSentDate() throws Exception {
        Date sentDate = mimeMessage.getSentDate();
        System.out.println("发送日期 原始类型: " + dateFormat);
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String strSentDate = format.format(sentDate);
        System.out.println("发送日期 可读类型: " + strSentDate);
        return strSentDate;
    }
 
    /**
     * 　*　获得邮件正文内容 　
     */
    public String getBodyText() {
        return bodyText.toString();
    }
 
    /**
     * 　　*　解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件
     * 　　*　主要是根据MimeType类型的不同执行不同的操作，一步一步的解析 　　
     */
 
    public void getMailContent(Part part) throws Exception {
    	
    	 //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
        if (part.isMimeType("text/*") && !isContainTextAttach) {  
        	bodyText.append(part.getContent().toString());  
        } else if (part.isMimeType("message/rfc822")) {   
        	getMailContent((Part)part.getContent());  
        } else if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                getMailContent(bodyPart);  
            }  
        }  
 
       /* String contentType = part.getContentType();
        // 获得邮件的MimeType
        System.out.println("邮件的MimeType: " + contentType);
 
        int nameIndex = contentType.indexOf("name");
 
        boolean conName = false;
 
        if (nameIndex != -1) {
            conName = true;
        }
 
        System.out.println("邮件内容的类型: " + contentType);
 
        if (part.isMimeType("text/plain") && conName == false) {
            // text/plain 类型
            bodyText.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && conName == false) {
            // text/html 类型
            bodyText.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            // multipart/*
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            // message/rfc822
            getMailContent((Part) part.getContent());
        } else {
 
        }*/
    }
 
    /**
     *   * 判断此邮件是否需要回执  需要"true",反之"false"  
     */
    public boolean getReplySign() throws MessagingException {
 
        boolean replySign = false;
 
        String needReply[] = mimeMessage
                .getHeader("Disposition-Notification-To");
 
        if (needReply != null) {
            replySign = true;
        }
        if (replySign) {
            System.out.println("该邮件需要回复");
        } else {
            System.out.println("该邮件不需要回复");
        }
        return replySign;
    }
 
    /**
     * 鑾峰緱姝ら偖浠剁殑Message-ID   
     */
    public String getMessageId() throws MessagingException {
        String messageID = mimeMessage.getMessageID();
        System.out.println("邮件ID: " + messageID);
        return messageID;
    }
 
    /**
     * 判断此邮件是否已读未读false,反之true
     */
    public boolean isNew() throws MessagingException {
        boolean isNew = false;
        Flags flags = ((Message) mimeMessage).getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        System.out.println("flags的长度: " + flag.length);
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isNew = true;
                System.out.println("seen email...");
                // break;
            }
        }
        return isNew;
    }
 
    /**
     * 判断是否包含附件  
     */
    public boolean isContainAttach(Part part) throws Exception {
        boolean attachFlag = false;
        // String contentType = part.getContentType();
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if ((disposition != null)
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition
                                .equals(Part.INLINE))))
                    attachFlag = true;
                else if (mPart.isMimeType("multipart/*")) {
                    attachFlag = isContainAttach((Part) mPart);
                } else {
                    String conType = mPart.getContentType();
 
                    if (conType.toLowerCase().indexOf("application") != -1)
                        attachFlag = true;
                    if (conType.toLowerCase().indexOf("name") != -1)
                        attachFlag = true;
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            attachFlag = isContainAttach((Part) part.getContent());
        }
        return attachFlag;
    }
 
    /**
     * 【保存附件】
     */
    public void saveAttachMent(Part part) throws Exception {
        String fileName = "";
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null)
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition
                        .equals(Part.INLINE)))) {
                    fileName = mpart.getFileName();
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    if (fileName.toLowerCase().indexOf("gbk") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    saveFile(fileName, mpart.getInputStream());
                } else if (mpart.isMimeType("multipart/*")) {
                    saveAttachMent(mpart);
                } else {
                    fileName = mpart.getFileName();
                    if ((fileName != null)) {
                        fileName = MimeUtility.decodeText(fileName);
                        saveFile(fileName, mpart.getInputStream());
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachMent((Part) part.getContent());
        }
    }
    /**
     * 【真正的保存附件到指定目录里】
     */
    private void saveFile(String fileName, InputStream in) throws Exception {
        String osName = System.getProperty("os.name");
        String storedir = saveAttachPath;
        String separator = "";
        if (osName == null)
            osName = "";
        if (osName.toLowerCase().indexOf("win") != -1) {
            separator = "\\";
            if (storedir == null || storedir.equals(""))
                storedir = "D://";
        } else {
            separator = "/";
            storedir = "/tmp";
        }
        fileName = fileName.substring(0,fileName.lastIndexOf("."));
        StringBuffer sb = new StringBuffer("cid:");
        File storefile = new File(storedir + separator + fileName);
        System.out.println("storefile's path: " + storefile.getAbsolutePath());
        for (int i = 0; storefile.exists(); i++) {
            storefile = new File(storedir + separator + fileName + i);
        }
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(storefile));
            bis = new BufferedInputStream(in);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
                bos.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("文件保存失败!");
        } finally {
            bos.close();
            bis.close();
        }
    }
 
    /**
     * 设置附件存放路径
     */
    public void setAttachPath(String attachPath) {
        this.saveAttachPath = attachPath;
    }
 
    /**
     *  * 获得日期显示格式
     */
    public void setDateFormat(String format) throws Exception {
        this.dateFormat = format;
    }
 
    /**
     *  * 获得附件存放路径
     */
    public String getAttachPath() {
        return saveAttachPath;
    }
    //替换所有的cid
    public String replaceLocalPath(String content) {
        return content.replaceAll("<img src=\"cid:","<img src=\"").toString();
        
    }

    /**
     * ReceiveEmail测试类
     */
    public static void main(String args[]) throws Exception {
        String host = "imap.163.com";
        String username = "w1647579959@163.com";
        String password = "123456a";
 
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
 
        Store store = session.getStore("imap");
        store.connect(host, username, password);
 
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        System.out.println(folder.getUnreadMessageCount());
        Message message[] = folder.getMessages();
        System.out.println("邮件数量:　" + message.length);
        ShowMail re = null;
        //获取未读邮件
		Message[] messages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());
		for (Message message2 : messages) {
			  re = new ShowMail((MimeMessage)message2);
			  String allname = re.getFrom();
			  System.out.println( "发送人地址: " + re.getFrom());
			  re.getMailContent((Part) message2);
			  System.out.println("正文内容\r\n" + re.getBodyText());
			  String hostName = allname.substring(allname.lastIndexOf("<")+1, allname.length()-1);
			  System.out.println(hostName);
			  //message2.setFlag(Flags.Flag.SEEN, true);
		}
    /*  for (int i = 6; i <= 6; i++) {
            re = new ShowMail((MimeMessage) message[i]);
            System.out.println("邮件" + i + "主题: " + re.getSubject());
            System.out.println("邮件" + i + "发送时间: " + re.getSentDate());
            System.out.println("邮件" + i + "是否需要回复： " + re.getReplySign());
            System.out.println("邮件" + i + "是否已读: " + re.isNew());
            System.out.println("邮件" + i + "是否包含插件: " + re.isContainAttach((Part) message[i]));
            System.out.println("邮件" + i + "发送人地址: " + re.getFrom());
            System.out.println("邮件" + i + " 收信人地址: " + re.getMailAddress("to"));
            System.out.println("邮件" + i + " 抄送: " + re.getMailAddress("cc"));
            System.out.println("邮件" + i + " 暗抄: " + re.getMailAddress("bcc"));
            re.setDateFormat("yy年MM月dd日  HH:mm");
            System.out.println("邮件" + i + "发送时间:" + re.getSentDate());
            System.out.println("邮件" + i + "邮件ID: " + re.getMessageId());
            re.getMailContent((Part) message[i]);
            System.out.println("邮件" + i + "正文内容\r\n" + re.getBodyText());
            System.out.println("替换后"+re.replaceLocalPath(re.getBodyText()));
            re.setAttachPath(new File("").getAbsolutePath());
            re.saveAttachMent((Part) message[i]);
        }*/
    }
}

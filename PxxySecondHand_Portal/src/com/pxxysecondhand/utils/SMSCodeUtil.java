package com.pxxysecondhand.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SMSCodeUtil {
	
	@Value("${SMS_ACCESSKEYID}")
	private String SMS_ACCESSKEYID;
	@Value("${SMS_ACCESSKEYSECRET}")
	private String SMS_ACCESSKEYSECRET;
	@Value("${SMS_SIGNNAME}")
	private String SMS_SIGNNAME;
	@Value("${SMS_TEMPLATECODE}")
	private String SMS_TEMPLATECODE;
	
	//����ע�����֤��
	public  String sendSmsMessage(String phonenumber,String code) throws ServerException, ClientException {
		return sendBasicSmsMessage(phonenumber,code,SMS_TEMPLATECODE);
	}
	
	//���ͽ���ȷ�ϵ���֤��
	public  String sendSmsConfirmMessage(String phonenumber,String code) throws Exception {
		String sendSmsResponse =  sendBasicSmsMessage(phonenumber,code,SMS_TEMPLATECODE);
		if(sendSmsResponse == null || !sendSmsResponse.equals("OK"))
			throw new Exception(sendSmsResponse);
		return sendSmsResponse;
	}
	public  String sendBasicSmsMessage(String phonenumber,String code,String SMS_TEMPLATECODE) throws ServerException, ClientException {
		  //���ó�ʱʱ��-�����е���
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //��ʼ��ascClient��Ҫ�ļ�������
        final String product = "Dysmsapi";//����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
        final String domain = "dysmsapi.aliyuncs.com";//����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�
        //�滻�����AK
        final String accessKeyId = SMS_ACCESSKEYID;//���accessKeyId,�ο����ĵ�����2
        final String accessKeySecret = SMS_ACCESSKEYSECRET;//���accessKeySecret���ο����ĵ�����2
        //��ʼ��ascClient,��ʱ��֧�ֶ�region�������޸ģ�
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
        accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
         //��װ�������
         SendSmsRequest request = new SendSmsRequest();
         //ʹ��post�ύ
         request.setMethod(MethodType.POST);
         //����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ�����͹���/�۰�̨��Ϣʱ�����պ����ʽΪ��������+���룬�硰85200000000��
         request.setPhoneNumbers(phonenumber);
         //����:����ǩ��-���ڶ��ſ���̨���ҵ�
         request.setSignName(SMS_SIGNNAME);
         //����:����ģ��-���ڶ��ſ���̨���ҵ������͹���/�۰�̨��Ϣʱ����ʹ�ù���/�۰�̨����ģ��
         request.setTemplateCode(SMS_TEMPLATECODE);
         //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
         //������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
         request.setTemplateParam("{\"code\":\""+code+"\"}");
         //��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
         //request.setSmsUpExtendCode("90997");
         //��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
         request.setOutId("yourOutId");
        //����ʧ���������ClientException�쳣
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
        //����ɹ�
        	System.out.println("���ͳɹ�");
        }else{
        	System.out.println("���ŷ���ʧ��"+sendSmsResponse.getCode());
        }
        return sendSmsResponse.getCode();
	}
}

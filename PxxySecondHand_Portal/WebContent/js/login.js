btnDisabled = false;
$(function() {
	/*
	 * 1. 让登录按钮在得到和失去焦点时切换图片
	 */
	$("#submit").hover(
		function() {
			$("#submit").attr("src", "image/login2.jpg");
		},
		function() {
			$("#submit").attr("src", "image/login1.jpg");
		}
	);
	/**
	 * 用户名输入框失去验证用户名
	 */
	$("#loginname").blur(
		function(){
		   validateUsername();
		}
	);
	
	/**
	 * 用户名输入框得到焦点
	 */
	$("#loginname").focus(
		function(){
			$("#loginnameError").text("");
			showError($("#loginnameError"));
		}
	);
	
	/**
	 * 密码输入框失去焦点验证密码
	 */
	$("#loginpass").blur(
		function(){
			validatePassword();
		}
	);
	
	/**
	 * 密码输入框得到焦点
	 */
	$("#loginpass").focus(
		function(){
			$("#loginpassError").text("");
			showError($("#loginpassError"));
		}
	);
	/**
	 * 图形验证码输入框失去焦点
	 */
	$("#verifyCode").blur(
		function(){
			validateVerifyCode();
		}
	);
	
	/**
	 * 图形密码输入框得到焦点
	 */
	$("#verifyCode").focus(
		function(){
			$("#verifyCodeError").text("");
			showError($("#verifyCodeError"));
		}
	);
	/**
	 * 手机号码框得到焦点
	 */
	$("#loginphonenumber").focus(
			function(){
				$("#loginphonenumberError").text("");
				showError($("#loginphonenumberError"));
			}
		);
	/**
	 * 手机号码输入框失去焦点
	 */
	$("#loginphonenumber").blur(
		function(){
			validatePhonenumber();
		}
	);
	/**
	 * 短信验证码框得到焦点
	 */
	$("#smsCode").focus(
			function(){
				$("#smsCodeError").text("");
				showError($("#smsCodeError"));
			}
		);
	/**
	 * 短信验证码框失去焦点
	 */
	$("#smsCode").blur(
		function(){
			validatesmsCode();
		}
	);
	/**
	 * 用户名密码登录表单提交
	 */
	$("#submit").click(
		function(){
			if(validatePasswordFormData()){
				var username = $("#loginname").val();
				var password = $("#loginpass").val();
				var verifyCode = $("#verifyCode").val();
				$.ajax({
					async:false,
					cache:false,
					url:"login/doLoginByPass.do",
					data:{username:username,password:password,verifyCode:verifyCode},
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.status=="400"){
							$("#verifyCodeError").text("验证码错误");
							showError($("#verifyCodeError"));
						}else if(data.status=="401"){
							$("#loginnameError").text("用户名或密码错误");
							showError($("#loginnameError"));
						}else if(data.status=="200"){
							swal("登录提示", "登录成功!即将转跳","success").then(()=>{
								loginSuccess();
							})
						}
					  
					}
				});
			}
			return false;
		}
	);
	/**
	 * 鼠标悬浮在手机验证码框
	 */
	$("#submitBtn").hover(
		function(){
			$("#submitBtn").css("backgroundColor","#ff6600");
		},
		function(){
			$("#submitBtn").css("backgroundColor","#ff8c00");
		}
	);
	/**
	 * 手机验证码登录表单提交
	 */
	$("#submitBtn").click(
		function(){
			if(validaSmsCodeForm()){
				var loginphonenumber = $("#loginphonenumber").val();
				var smsCode = $("#smsCode").val();
				$.ajax({
					async:false,
					cache:false,
					url:"login/doLoginBySmscode.do",
					data:{phonenumber:loginphonenumber,smsCode:smsCode},
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.status=="401"){
							sweetAlert(
									  '登录提示',
									  '验证码错误,请重新输入',
									  'error'
									)
						}else if(data.status=="200"){
							swal("登录提示!", "登录成功!即将转跳","success").then(()=>{
								loginSuccess();
							})
						}else{
							sweetAlert(
									  '哎呦……',
									  '出错了！',
									  'error'
									)
						}
					  
					}
				});
			}
			return false;
		}
	);
	
	/**
	 * 点击发送验证码按钮
	 */
	$("#sms-code").click(
		function(){
			if(!btnDisabled){
				var b = true;
				if(b){
					btnDisabled = true;
					var timeoutId = timewait(60,this);
					$(this).addClass("sms_disabled").removeClass("send_sms");
					sendMSM(timeoutId);
				}
			}
		}
	);
	
})
/**
 * 前端验证用户名框格式
 */
function validateUsername(){
	var bool = true;
	var id="loginname";
	var errorId = id+"Error";
	var text = $("#"+id).val();
	if(!text||text==""){
		$("#"+errorId).text("请输入用户名");
		showError($("#"+errorId));
		bool = false;
	}
	return bool;
}
/**
 * 前端验证密码框格式
 */
function validatePassword(){
	var bool = true;
	var id="loginpass";
	var errorId = id+"Error";
	var text = $("#"+id).val();
	if(!text||text==""){
		$("#"+errorId).text("请输入密码");
		showError($("#"+errorId));
		bool = false;
	}
	return bool;
}
/**
 * 验证图形验证码框格式
 * @returns
 */
function validateVerifyCode(){
	var bool = true;
	var id="verifyCode";
	var errorId = id+"Error";
	var text = $("#"+id).val();
	if(!text||text==""){
		$("#"+errorId).text("请输入验证码");
		showError($("#"+errorId));
		bool = false;
	}
	return bool;
}
/**
 * 验证手机号码框格式
 * @returns
 */
function validatePhonenumber(){
	var bool = true;
	var id = "loginphonenumber";
	var errorId = id+"Error";
	var text = $("#"+id).val();
	if(!text||text==""){
		$("#"+errorId).text("手机号码不能为空");
		showError($("#"+errorId));
		bool = false;
	}else if(!(/^(1[34578][0-9]{9})$/.test(text))){
		$("#"+errorId).text("手机号码格式错误");
		showError($("#"+errorId));
		/*  /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/ */
		bool = false;
	}
	return bool;
}
/**
 * 验证短信验证码框格式
 * @returns
 */
function validateSmsCode(){
	var bool = true;
	var id = "smsCode";
	var errorId = id+"Error";
	var text = $("#"+id).val();
	if(!text||text==""){
		$("#"+errorId).text("验证码不能为空");
		showError($("#"+errorId));
		bool = false;
	}
	return bool;
}
/**
 * 验证密码登录表单数据
 */
function validatePasswordFormData(){
	var bool = true;
	var bool1 = validateUsername();
	var bool2 = validatePassword();
	var bool3 = validateVerifyCode();
	bool = bool1&&bool2&&bool3;
	return bool;
}
/**
 * 验证验证码登录表单
 * @returns
 */
function validaSmsCodeForm(){
	var bool = true;
	var bool1 = validatePhonenumber();
	var bool2 = validateSmsCode();
	bool = bool1&&bool2;
	return bool;
}
/**
 * 发送验证码
 */
function sendMSM(timeoutId){
	var phonenumber = $("#loginphonenumber").val();
	if(phonenumber==""){
		return;
	}
	$.ajax({
		async:false,
		cache:false,
		url:"login/sendSmsMessage.do",
		data:{phonenumber:phonenumber},
		dataType:"json",
		type:"get",
		success:function(data){
			if(data.status=="500"){
				btnDisabled = false;
				clearTimeout(timeoutId);
				$("#sms-code").removeClass("sms_disabled").addClass("send_sms");
				$("#sms-code").html("发送验证码");
				$("#loginphonenumberError").text(data.msg);
				showError($("#loginphonenumberError"));
			}
		}
		
	});
}
/**
 * 登录成功执行函数
 */
function loginSuccess(){
	var prePage = $("#prePage").val();
	if(prePage==null||prePage==""){
		window.location.href="index.html?prePage=login";
	}
	window.location.href=prePage+".html?prePage=login";
}
/**
 * 显示或隐藏错误信息
 * @returns
 */
function showError(ele){
	var text = ele.text();
	if(!text||text==""){
		ele.css("display","none");
	}else{
		ele.css("display","");
	}
}
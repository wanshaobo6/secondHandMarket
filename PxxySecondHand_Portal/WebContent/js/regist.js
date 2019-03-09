//发送验证码是否可用
var btnDisabled = false;
$(function(){
	
	$(".tr_sms").css("display","table-row");
	$("#loginname").focus();
	/**
	 * 判断labelError中是否有文字如果没有隐藏错误
	 */
	$(".labelError").each(function(){
		showError($(this));
	});
	/**
	 * 悬浮注册按钮时加重
	 */
	$("#submitBtn").hover(
			function(){
				$("#submitBtn").attr("src","image/regist2.jpg");
			},
			function(){
				$("#submitBtn").attr("src","image/regist1.jpg");
			}
	);
	//电话号码框得到焦点
	$("#loginphonenumber").focus(
		function(){
			$("#loginphonenumberError").text("");
			showError($("#loginphonenumberError"));
		}
	);
	//电话号码框失去焦点
	$("#loginphonenumber").blur(
			function(){
				validatePhoneNumber();
			}
	);
	//验证码框得到焦点
	$("#smscode").focus(
			function(){
				$("#smscodeError").text("");
				showError($("#smscodeError"));
			}
		);
	/**
	 * 点击发送验证码按钮
	 */
	$("#sms-code").click(
		function(){
			if(!btnDisabled){
				var b = validatePhoneNumber();
				if(b){
					btnDisabled = true;
					var timeoutId = timewait(60,this);
					$(this).addClass("sms_disabled").removeClass("send_sms");
					sendMSM(timeoutId);
				}
			}
		}
	);
	
	/**
	 * 取消协议框
	 */
	$("#checkrect").click(
		function(){
			$("#checkrect").css("display","none");
			$("#uncheckrect").css("display","");
		}
	);
	/**
	 * 勾选协议框
	 */
	$("#uncheckrect").click(
			function(){
				$("#uncheckrect").css("display","none");
				$("#checkrect").css("display","");
				$("#protocolError").text("");
				showError($("#protocolError"));
			}
		);
	
    /**
     * 用户名框失去焦点 触发判断时间
     */
	$("#loginname").blur(
		function(){
			validateUserName();
		}
	);
	/**
	 * 用户名框得到焦点  消除错误
	 */
	$("#loginname").focus(
		function(){
			$("#loginnameError").text("");
			showError($("#loginnameError"));
		}
	);
	/**
	 * 密码框失去焦点 判断非空和格式
	 */
	$("#loginpass").blur(
		function(){
			validatePassword();
		}
	);
	/**
	 * 密码框得到焦点 
	 */
	$("#loginpass").focus(
			function(){
				$("#loginpassError").text("");
				showError($("#loginpassError"));
			}
		);
	/**
	 * 确认密码框失去焦点 判断一致
	 */
	$("#reloginpass").blur(
		function(){
			validateRePassword();
		}
	);
	/**
	 * 确认密码框得到焦点 
	 */
	$("#reloginpass").focus(
		function(){
			$("#reloginpassError").text("");
			showError($("#reloginpassError"));
		}
	);
	/**
	 * 院系名输入框失去焦点
	 */
	$("#department").blur(
		function(){
			validateDepartment();
		}
	);
	/**
	 * 院系名输入框得到焦点
	 */
	$("#department").focus(
		function(){
			$("#departmentError").text("");
			showError($("#departmentError"));
		}
	);
	/**
	 * 班级名输入框失去焦点
	 */
	$("#classname").blur(
		function(){
			validateClassname();
		}
	);
	/**
	 * 班级名输入框得到焦点
	 */
	$("#classname").focus(
			function(){
				$("#classnameError").text("");
				showError($("#classnameError"));
			}
	);
	/**
	 * 确认注册
	 */
	$("#submitBtn").click(
		function(){
			if(checkAllFormDate()){
				var username = $("#loginname").val();
				var password = $("#loginpass").val();
				var repassword = $("#reloginpass").val();
				var department = $("#department").val();
				var classname = $("#classname").val();
				var phonenumber = $("#loginphonenumber").val();
				var smscode = $("#smscode").val();
				$.ajax({
					async:false,
					cache:false,
					url:"regist/addUser.do",
					data:{username:username,password:password,repassword:repassword,department:department
							,classname:classname,phonenumber:phonenumber,smscode:smscode},
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.status=="200"){
							var res = confirm("注册成功，需要登录吗");
							if(res){
								window.location.href="login.html?prePage=login";
							}else{
								var prePage = $("#prePage").val();
								if(prePage==null){
									window.location.href="index.html?prePage=login";
								}
								window.location.href=prePage+".html?prePage=login";
							}
						}
					  
					}
				});
				  }
			return false;
			}
		)
		
	});

/**
 * 验证码倒计时
 */
function timewait(time,btn){
	var timeoutid = setTimeout(
		function(){
			if(time>=0){
				btn.innerHTML=time+"s秒后重试";
				time--;
				timewait(time,btn);
			}else{
				btn.innerHTML = "发送验证码";
				btnDisabled = false;
				$(btn).addClass("send_sms").removeClass("sms_disabled");
			}
		},1000);
	return timeoutid;
}
/**
 * 发送验证码
 */
function sendMSM(timeoutId){
	var name = $("#loginname").val();
	var phonenumber = $("#loginphonenumber").val();
	if(phonenumber==""){
		return;
	}
	$.ajax({
		async:false,
		cache:false,
		url:"regist/sendSmsMessage.do",
		data:{name:name,phonenumber:phonenumber},
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
 * 
 * 验证手机号码格式和是否存在
 */
function validatePhoneNumber(){
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
	}else{
		$.ajax({
			async:false,
			cache:false,
			url:"regist/validatePhonenumber.do",
			data:{phonenumber:text},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.status=="200"&&data.msg=="1"){
					$("#"+errorId).text("手机号码已经存在");
					showError($("#"+errorId));
					bool = false;
				}else{
					if(data.status=="200"&&data.msg=="0"){
						bool = true;
					}
					
				}
			}
			
		});
		
		
	}
	return bool;
}
/**
 * 验证用户名格式和用户名是否存在
 * 用户名长度为2-7
 * @returns
 */
function validateUserName(){
	var bool = true;
	var id = "loginname";
	var errorId = id+"Error";
	var text = $("#"+id).val();
	if(!text||text==""){
		$("#"+errorId).text("用户名不能为空");
		showError($("#"+errorId));
		bool = false;
	}else if(text.length<2||text.length>7){
		$("#"+errorId).text("用户名的长度必须为2-7");
		showError($("#"+errorId));
		bool = false;
	}else{
		$.ajax({
			async:true,
			cache:false,
			url:"regist/validateUsername.do",
			data:{username:text},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.msg=="1"){
					$("#"+errorId).text("用户名已经存在");
					showError($("#"+errorId));
				}else{
					if(data.status=="200"&&data.msg=="0"){
						bool = true;
					}
					
				}
			}
			
		});
	}
	return bool;
}
/**
 * 验证用户密码格式是否正确
 */
	function validatePassword(){
		var bool = true;
		var id = "loginpass";
		var errorId = id+"Error";
		var text = $("#"+id).val();
		if(!text||text==""){
			$("#"+errorId).text("密码不能为空");
			showError($("#"+errorId));
			bool = false;
		}else if(text.length>20||text.length<5){
			$("#"+errorId).text("密码的长度必须在5-20之间");
			showError($("#"+errorId));
			bool = false;
			
		}else if((/^\d+$/.test(text))){
			$("#"+errorId).text("密码中必须含有字母");
			showError($("#"+errorId));
			bool = false;
		}
		return bool;
		
	}
	
/**
 * 验证重新输入密码格式是否正确
 */
	function validateRePassword(){
		var bool = true;
		var loginpassId = "loginpass";
		var loginpass = $("#"+loginpassId).val();
		var reloginpassId = "reloginpass";
		var errorId = reloginpassId+"Error";
		var reloginpass = $("#"+reloginpassId).val();
		if(!validatePassword()){
			bool = false;
		}else if(reloginpass!=loginpass){
			$("#"+errorId).text("两次输入的密码不一致");
			showError($("#"+errorId));
			bool = false;
		}
		return bool;
		
	}
	
/**
 * 检查院系名格式
 */
	 function validateDepartment(){
		var bool = true;
		var id="department";
		var errorId=id+"Error";
		var text = $("#"+id).val();
		if(!text||text==""){
			$("#"+errorId).text("部门号不能为空");
			showError($("#"+errorId));
			bool = false;
		}
		return bool;
	 }
 /**
  * 检查院系名格式
  */
 	 function validateClassname(){
 		var bool = true;
 		var id="classname";
 		var errorId=id+"Error";
 		var text = $("#"+id).val();
 		if(!text||text==""){
 			$("#"+errorId).text("班级名不能为空");
 			showError($("#"+errorId));
 			bool = false;
 		}
 		return bool;
 	 }
/**
 * 检查验证码框格式
 */
	function validateSmsCode(){
		var bool = true;
		var id = "smscode";
		var errorId = id+"Error";
		var text = $("#"+id).val();
		if(text.length!=6){
			$("#"+errorId).text("验证码不正确");
			showError($("#"+errorId));
			bool = false;
		}
		return bool;
	}
	
/**
 * 检查协议框是否被勾选
 */
	 function checkCheckrect(){
		 var bool = true;
		 var errorId = "protocolError";
		var displayable = $("#checkrect").css("display"); 
		if(displayable=="none")
		 {
			$("#"+errorId).text("请先同意服务条款");
			showError($("#"+errorId));
			bool = false;
		 }
		return bool;
	 }

/**
 * 检查所有的表单数据
 */
	 function checkAllFormDate(){
		 var bool = true;
		 var bool1 = validatePhoneNumber();
		 var bool2 = validateUserName();
		 var bool3 = validatePassword();
		 var bool4 = validateSmsCode();
		 var bool5 = checkCheckrect();
		 
		 bool = bool1&&bool2&&bool3&&bool4&&bool5;
		 return bool;
	 }
	 

/**
 * 
 * 传入对象有文本则显示错误信息
 * @param ele
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



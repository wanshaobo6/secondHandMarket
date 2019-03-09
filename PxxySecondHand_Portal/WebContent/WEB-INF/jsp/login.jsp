<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/reset.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert2.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
	<!-- 保存上一页地址 -->
	<input type="hidden" id="prePage" name="prePage" value="${prePage}">
	<img  src="${pageContext.request.contextPath }/image/background_big_loginimage.png" id="background" >
	<!--S=logoAndLoginForm  -->
	<div class="main">
		<div class="logo"></div>
		<div>
			<div class="login1 fl">
				<div class="login2">
					<div class="loginTopDiv">
						<ul id="tab" >
  							<li id="tab1" value="1" style="border-bottom:1px solid #f5f5f5">用户名登录</li>
 							<li id="tab2" value="2" style="border-bottom:1px solid #f5f5f5">手机号登录</li>
						</ul>
					</div>
					<div id="container">
					<div class="formDiv1">
						<form action="javascript:void(0)" method="post">
						<table>
	                    <tr>
	                      <td width="50"></td>
	                      <td><label class="error" id="msg">${msg }</label>
	                      </td>
	                    </tr>
	                    <tr>
	                      <td width="50">用户名</td>
	                      <td><input class="input" type="text" name="username" id="loginname" placeholder="请输入用户名" value="${user.loginname }"/></td>
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                      <td><label id="loginnameError" class="error">${errors.loginnameError }</label></td>
	                    </tr>
	                    <tr>
	                      <td>密　码</td>
	                      <td><input class="input" type="password" name="password" id="loginpass" placeholder="请输入密码" value="${user.loginpass }"/></td>
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                      <td><label id="loginpassError" class="error">${errors.loginpassError }</label></td>
	                    </tr>
	                    <tr>
	                      <td>验证码</td>
	                      <td>
	                        <input class="input yzm" type="text" name="verifyCode" id="verifyCode" value="${user.inputverifycode }"/>
	                        <img id="vCode" src="${pageContext.request.contextPath }/verifyCodeServlet"/>
	                        <a id="verifyCode" href="javascript:changeVerifyCodeImage('vCode')">换张图</a>
	                      </td>
	                    </tr>
	                    <tr>
	                      <td height="20px">&nbsp;</td>
	                      <td><label id="verifyCodeError" class="error">${errors.verifycodeError }</label></td>
	                    </tr>
	                      <tr>
	                      <td height="20px">&nbsp;</td>
	                      <td><div class="fr"><a href="javascript:void(0)">忘记密码</a></div></td>
	                    </tr>
	                    <tr>
	                      <td>&nbsp;</td>
	                      <td align="left">
	                        <input type="image" id="submit" src="${pageContext.request.contextPath }/image/login1.jpg" class="loginBtn"/>
	                          <span class="registbtnspan">
	               				 <a href="${pageContext.request.contextPath }/regist.html" class="registBtn"></a>
	            			  </span>
	                      </td>
	                    </tr>																				
                 	</table>
					</form>
					</div>
					<div class="formDiv2">
					<form action="javascript:void(0)" method="post">
					<table>
					<tr>
	                      <td width="50">手机号</td>
	                      <td><input class="input" type="text"  id="loginphonenumber" placeholder="请输入手机号" /></td>
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                      <td><label class="error" id="loginphonenumberError">${errors.loginphonenumberError}</label></td>
	                    </tr>
	                    <tr>
	                      <td>验证码</td>
	                      <td><input class="verifyCodeInput" type="text" name="smsCode" id="smsCode" placeholder="请输入验证码" /> <a class="send_sms" id="sms-code" >发送验证码</a></td>
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                      <td><label class="error" id="smsCodeError">${errors.smsCodeError}</label></td>
	                    </tr>
	                    <tr class="sendCode">
						<td ></td>
						<td ></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="button" accesskey="" value="立即登录" name="submitBtn" id="submitBtn"/>
						</td>
					</tr>
					</table>
					</form>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    <script type="text/javascript">

  var $form = $('#container>div')
  

  var currIndex = 0 
  $('#tab>li').click(function () { 
	$form[currIndex].style.display = 'none';
    var index = $(this).index();
   $("#tab"+(index+1)).css("backgroundColor","#c0c0c0");
   if(index==1)
	   $("#tab"+(index)).css("backgroundColor","transparent");
   $("#tab"+(index+2)).css("backgroundColor","transparent");
    $form[index].style.display = 'block';
    currIndex = index;
  })


</script>
	<!--E=logoAndLoginForm  -->
</body>
</html>
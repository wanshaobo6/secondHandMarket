<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/regist.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/regist.js"></script>
</head>
<body>	
  <div class="main">
	<div class="mainOuter">
		<!-- S=RegisterForm -->
		<div id="divMain" >
			<div id="divTitle"><span id="spanTitle">新用户注册</span></div>
		<div id="divBody">
			<form id="registForm" action="javascript:void(0)" method="post">
					<input type="hidden" id="prePage" name="prePage" value="${prePage}">
					<table id="tableForm">
						<tr ><td class="tdText"></td>
							<td class="tdInput"><label class="labelError" id="fatalError">${errors.fatalError }</label></td>
							<td class="tdError"></td>
						</tr>
						<tr ><td class="tdText">用户名:</td>
							<td class="tdInput"><input type="text" name="username" id="loginname" class="inputClass" value="${user.username}" ></td>
							<td class="tdError"><label class="labelError" id="loginnameError">${errors.usernameError }</label></td>
						</tr>
						<tr><td class="tdText" >登陆密码:</td>
							<td><input type="password" name="password" id="loginpass"   class="inputClass"  value="${user.password}"></td>
							<td><label class="labelError" id="loginpassError">${errors.passwordError }</label></td>
						</tr>
						<tr><td class="tdText">确认密码:</td>
							<td><input type="password" name="repassword" id="reloginpass"   class="inputClass"  value="${user.password}" ></td>
							<td><label class="labelError" id="reloginpassError">${errors.repasswordError }</label></td>
						</tr>
						<tr ><td class="tdText">院系名:</td>
							<td class="tdInput"><input type="text" name="department" id="department"  class="inputClass" value="${user.department}"  ></td>
							<td class="tdError"><label class="labelError" id="departmentError">${errors.departmentError }</label></td>
						</tr>
						<tr ><td class="tdText">班级名:</td>
							<td class="tdInput"><input type="text" name="classname" id="classname"  class="inputClass" value="${user.classname}" ></td>
							<td class="tdError"><label class="labelError" id="classnameError">${errors.classnameError }</label></td>
						</tr>
						<tr><td class="tdText">手机号码:</td>
							<td><input type="text" name="phonenumber" id="loginphonenumber" class="inputClass" value="${user.phonenumber}" ></td>
							<td><label class="labelError" id="loginphonenumberError">${errors.phonenumberError }</label></td>
						</tr>
						<tr class="tr_sms">
							<td ></td>
							<td ><input type="text" name="smscode" id="smscode"  class="smsInput"><a class="send_sms" id="sms-code" >发送验证码</a></td>
							<td class="tdError"><label class="labelError" id="smscodeError">${errors.smsCodeError}</label></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<label for="agree" class="checkbox">
									<img src="${pageContext.request.contextPath}/image/checkbox_check.png" id="checkrect"  style="display: none;">
									<img src="${pageContext.request.contextPath}/image/checkbox_normal.png" id="uncheckrect"  >&nbsp;我已阅读并同意相关<a href="javascript:void(0)">服务条款</a>和<a href="javascript:void(0)">隐私政策</a></label>
							</td>
							<td class="tdError"><label class="labelError" id="protocolError"></label></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="image" src="${pageContext.request.contextPath}/image/regist1.jpg" name="submitBtn" id="submitBtn"/>
							</td>
						</tr>
						
			</table>
			</form>
			</div>
		</div>
	<!-- E=RegisterForm-->
	</div> 
  </div>
</body>
</html>
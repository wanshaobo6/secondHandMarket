<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>密码重置</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/rename.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
</head>
		<body>
		<div class="main">
			<h1>密码重置</h1>
			<div class="step1">
				1短信验证
				
			</div>
			<i class="fa fa-arrow-right"></i>
			<div class="step2">
				2重置密码
			</div>
			<i class="fa fa-arrow-right"></i>
			<div class="step3">
				3完成
			</div>
			<div class="container">
			<div id="container1">
				<form action="">
					<table>
					<tr>
	                      <td width="50">手机号</td>
	                      <td><input class="input" type="text"  id="loginphonenumber" placeholder="请输入手机号" /></td>
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                      <td>
	                    </tr>
	                    <tr>
	                      <td>验证码</td>
	                      <td><input class="verifyCodeInput" type="text" name="verifyCode" id="verifyCode" placeholder="请输入验证码" /> 
	                      </td><td><input type="button" value="发送验证码" class="sendbtn" ></td>
	                      
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                    </tr>
	                    
	<td></td>
	<td>
	<input type="button" value="下一步"  name="submitBtn1" id="submitBtn1"/>
	</td>
	</tr>
	</table></form>
			</div>
			<div id="container2">
				<form action="">
					<table>  
					<tr>
	                      <td width="100">新&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
	                      <td><input class="input" type="text"  id="loginphonenumber" placeholder="请输入新密码" /></td>
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                      <td>
	                    </tr>
	                    <tr>
	                      <td width="100">确认新密码</td>
	                      <td><input class="verifyCodeInput" type="text" name="verifyCode" id="verifyCode" placeholder="再次输入新密码" /> 
	                      </td>
	                      
	                    </tr>
	                    <tr>
	                      <td height="20">&nbsp;</td>
	                    </tr>
	                    
	<td></td>
	<td>
	<input type="button" value="下一步"  name="submitBtn2" id="submitBtn2"/>
	</td>
	</tr>
	</table></form>
			</div>
			<div id="container3">
				重置密码成功
			</div>
			</div></div>
		 <script type="text/javascript">
		 	$('.step1').css('background-color','lightskyblue')
   	$('#container1').css('display','block');
   		$('#container2').css('display','none');
   			$('#container3').css('display','none');
    $('#submitBtn1').click(function () {
   	$('#container1').css('display','none');
   	$('#container3').css('display','none');
    $('.step2').css('background-color','lightskyblue')
	$('#container2').css('display','block');
})
$('#submitBtn2').click(function(){
   	$('#container1').css('display','none');
	$('#container2').css('display','none');
	$('#container3').css('display','block');
	$('.step3').css('background-color','lightskyblue')
})
</script>
	</body>
</html>

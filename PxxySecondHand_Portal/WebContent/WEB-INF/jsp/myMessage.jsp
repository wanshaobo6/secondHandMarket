<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/myMessage.css">
<title>Insert title here</title>
</head>
	<body>
		<c:forEach var="msg" items="${data.itemList }">
		<div class="shop" id="message">
			<div class="header" id="t2">
				<div class="headerLeft" id="touxiang">
					<img src="img/touxiang1.jpg"  alt="touxiang"/>
				</div>
				<div class="headerMiddle" >
				<div class="headerMiddle-top" id="xingming"><b>${msg.fromUsername }</b></div>
				<div class="headerMiddle-bottom" id="time">留言时间：${msg.messageDate }</div>
				</div>
				<div class="headerRight" id="mes">留言信息：${msg.message }</div>
			</div>
			<div class="sheet" id="sangpin">
				<div class="imgs" id="tupian">
				 	<img  class="tom1" alt="图片" src="${msg.image }"/> 
				</div>
				<div class="btn">
				
					<div class="topic" id="guanjianci">${msg.itemName }</div>
				<div class="money" id="jiage">￥ <b style="color:red">${msg.currentPrice }</b></div>
				</div>
			</div>
						
		</div>
	</c:forEach>
	   <jsp:include page="/WEB-INF/commons/paginator.jsp"></jsp:include>
	</body>
</html>
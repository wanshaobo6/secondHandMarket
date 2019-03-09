<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>萍院要问</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/puNews.css"/>
</head>
<body>
	<jsp:include page="/WEB-INF/commons/top.jsp" ></jsp:include>
	
	<!--S=newsContent  -->
      <div class="newscontent">
        <h1 id="newstitle">${message.title}</h1>
        <div class="content">
          <div id="LeftTool" class="LeftTool">
          <!--内容-->
          <div class="content-article">
         	${message.content}
        </div>
      </div>
    </div>
	<!--E=newsContent -->
	
			
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<!-- E=division  -->
	
	<!-- S=copytright -->
	<jsp:include page="/WEB-INF/commons/copyright.jsp"></jsp:include>
	<!-- E=copytright -->
</body>
</html>
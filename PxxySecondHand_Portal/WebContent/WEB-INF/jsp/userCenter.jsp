<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/userCenter.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userCenter.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/commons/top.jsp"></jsp:include>
	<!-- S=content -->
	<div class="content">
		<!-- S=content_slidebar -->
		<div class="slidebar fl">
			<div class="userinfo">
				<div class="useravator fl">
					<img alt="用户头像" src="${pageContext.request.contextPath }/image/4_cred.gif">
				</div>
				<a class="usernick fl">happiness520520</a>
				<p class="usercredit"><img alt="140-190积分表示4心" src="${pageContext.request.contextPath }/image/4_cred.gif"></p>
			</div>
			<ul class="navigations">
				<li class="navselected"><a id="a1" href="${pageContext.request.contextPath}/item/showMyPublic.html" target="framecontent">我的闲置</a></li>
				<li class="navunselected"><a id="a5" href="${pageContext.request.contextPath}/trade/getAllOfMySelled.html" target="framecontent">我卖出的</a></li>
				<li class="navunselected" ><a id="a2"  href="${pageContext.request.contextPath}/trade/getAllOfMyTrade.html" target="framecontent">我买到的</a></li>
				<li class="navunselected"><a  id="a3" href="${pageContext.request.contextPath}/collection/getAllOfMyCollections.do" target="framecontent">我的收藏</a></li>
				<li class="navunselected"><a  id="a4" href="${pageContext.request.contextPath}/rename.html" target="framecontent">修改密码</a></li>
				<li class="navunselected"><a id="a6" href="javascript:void(0)" target="framecontent">我的资料</a></li>
			</ul>
		</div>
		<!-- E=content_slidebar -->
		<!-- S=content_maincontent -->
		<div class="maincontent fr">
			<div class="framecontent">
				<iframe id="userIframeContent" src="${pageContext.request.contextPath}/item/showMyPublic.do" name="framecontent" frameborder="0"    scrolling="no" ></iframe>
			</div>
		</div>
		<!-- E=content_maincontent -->
	</div>
	<!--E=content -->
	
			
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<!-- E=division  -->
	
	<!-- S=copytright -->
	<jsp:include page="/WEB-INF/commons/copyright.jsp"></jsp:include>
	<!-- E=copytright -->

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面顶端</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/top.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.cookie.js"></script>
</head>
<body>
	<!-- S=Head -->
	<div  class="head">
		<div class="head_m">
			<div class="head_r fr" id="loginbar" >
				欢迎来到跳蚤市场[
				<a href="${pageContext.request.contextPath}/login.html?prePage=index">登录</a>]&nbsp;|&nbsp;[<a href="${pageContext.request.contextPath}/regist.html?prePage=index">注册</a>]
			</div>
			<div class="head_l fl">
					<ul>
						<li class="h_fav"><a href="javascript:void(0)" title="萍院跳蚤市场" rel="sidebar" onclick="if(UA.indexOf('chrome') != -1){alert('请按快捷键Ctrl+D收藏本页，谢谢');return false;}window.external.addFavorite(this.href, this.title);return false;">收藏本页</a></li>
						<li class="h_wap"><img src="${pageContext.request.contextPath }/image/ico_wap.gif" ><a href="javascript:void(0)">手机版</a>&nbsp;</li>
					</ul>
				</div>
			</div>
	  </div>
	<!-- E=Head -->
	
	<!-- S=Padding -->
	<div class="hpadding">
	</div>
	<!-- E=Padding -->
	<!-- S=Search -->
	<div class="search">
		<div class="search_left fl">
			<a href="${pageContext.request.contextPath}/index.html"><s class="search_left_icon"><img alt="首页图标" style="width:200px;height: 125px;" src="${pageContext.request.contextPath}/image/newlog.png"></s></a>
		</div>
		<div class="search_right fl">
			<div class="search_right_head">
				<form id="searchForm" action="${pageContext.request.contextPath}/search/searchByKeywords.do" method="get">
					<div class="search_right_Content"><s></s>
						<input class="searchContent fl" type="text" name="keywords" placeholder="请输入关键字" value="${keywords}" >
					</div>
					<div>
						<input class="searchButton fl" id="searchBtn" type="button" value="搜    索 ">
					</div>
				</form>
			</div>
			<div id="hotSearch" class="search_right_foot" >
			</div>
		</div>
			<div class="search_wechat fr">
				<span>微信公众号</span>
				<img alt="微信公众号" src="${pageContext.request.contextPath }/image/wechat.png">
			</div>
	</div>
	<!-- E=Search -->
	
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<input type="hidden" id="currentUserId" />
	<!-- E=division  -->
	<!--查看当前登录用户  -->
	<script type="text/javascript">
		var isSuccessLogin = false;
		function checkLogin(){
			var _token = $.cookie('Login_Token');
			if(!_token){
				return ;
			}
			$.ajax({
					async:true,
					cache:false,
					url:"/PxxySecondHand_Portal/login/showCurrUser/"+_token+".do",
					dataType:"json",
					type:"get",
					success:function(data){
						if(data.status=="200"){
							isSuccessLogin = true;
							$(".showAfterLoginIn").css("display","inline");
							var obj = JSON.parse(data.data);
							var html ="<span class='f_red'>"+ obj.username+"</span>同学，欢迎来到跳蚤市场 <a href='javascript:quit()' class='f_quit_color'>[退出]</a>";	
							$("#loginbar").html(html);
							$("#currentUserId").val(obj.id);
							}else{
								$("#currentUserId").val("");
							}
						}
					}
				);
		}
		function quit(){
			var _token = $.cookie('Login_Token');
			if(!_token)
				return ;
			$.ajax({
				async:true,
				cache:false,
				url:"/PxxySecondHand_Portal/login/quit/"+_token+".do",
				dataType:"json",
				type:"get",
				success:function(data){
					if(data.status=="200"){
					alert("已安全退出");
					isSuccessLogin = false;
						var html ="	欢迎来到跳蚤市场[<a href='${pageContext.request.contextPath}/login.html?prePage=index'>登录</a>]&nbsp;|&nbsp;[<a href='${pageContext.request.contextPath}/regist.html?prePage=index'>注册</a>]";	
						$("#loginbar").html(html);
						$(".menu>li").each(function(index,e){
							if(index==0)
								return;
							$(this).remove();
						});
						$("#currentUserId").val("");
						//隐藏用户中心等按钮
						$(".showAfterLoginIn").css("display","none");
						}
					}
				});
		}
		function getPopulateSearchKeyword(){
			$.ajax({
				async:true,
				cache:false,
				url:"/PxxySecondHand_Portal/search/getPopulateSearchKeys.do",
				type:"get",
				success:function(data){
					if(data.status=="200"){
						var nameList = data.data;
						var aList = "热搜: ";
						nameList.forEach(n =>{
							aList +="<a href='#'>"+n+"</a> &nbsp"; 
						});
						$("#hotSearch").append(aList);
					}
				  }
				}
			);
		}
		$(function(){
			checkLogin();
			getPopulateSearchKeyword();
			$("#searchBtn").click(
				function(){
					$("#searchForm").submit();
				}		
			);
		}
	 );
	</script>
</body>
</html>

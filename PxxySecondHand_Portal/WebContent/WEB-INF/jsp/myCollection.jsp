<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的收藏</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/myCollection.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
</head>
<body>
<!-- S=maincontent -->
	<div class="maincontent">
		<c:choose>
		<c:when test="${fn:length(data.itemList)>0 }">
		<table class="soldoutitemlist" cellspacing="0">
			<colgroup>
				<col class="clo01">
				<col class="clo02">
				<col class="clo03">
			</colgroup>
			<!-- 每页6 条记录-->
			<c:forEach items="${data.itemList}" var="item">
			<thead >
			</thead>
				<tbody >
				<tr class="tradeinfo" >
					<fmt:formatDate value="${item.collectionTime}" pattern="yyyy-MM-dd HH:mm:ss" var="time"/>
					<td colspan="2"><span style="display:block;text-indent: -99999px;"></span>收藏时间:${time}</td>
					<td colspan="1"></td>
				</tr>
				<tr class="rowpadding">
					<td colspan="3"></td>
				</tr>
				<tr class="tradedetail">
					<td class="tradedetail_cell">
						<a class="tradeitem_pic">
							<img alt="闲置图片" src="${item.image}">
						</a>
						<div class="tradedetail_desc ">
							<p class="tradedetail_title">
								<a name="${item.itemId}" class="iwantit"  href="javascript:void(0)">${item.itemtitle}</a>
							</p>
							<p class="tradedetail_price">
								<em>¥</em>
								<b>${item.currprice}</b>
							</p>
						</div>
					</td>
					<td class="tradedetail_status">
						<!-- S=交易中状态 -->
						<div class="currstatus">
							<span class="currstatus_span">商品状态</span>
							<div class="buyerinfo">
								<c:choose>
									<c:when test="${item.isSoldOut eq '0'}">
										<div class="buyerinfo_row"><span>未被购买</span></div>
									</c:when>
									<c:otherwise>
										<div class="buyerinfo_row"><span>已被购买</span></div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</td>
					<td class="tradedetail_operation">
						<div class="tradedetail_operation_buttons">
							<c:if test="${item.isSoldOut eq '0'}">
							<button class="tradedetail_operation_button iwantit" name="${item.itemId}">我想要</button>  
							</c:if>
							<button id="del${item.itemId}" class="tradedetail_operation_button cancelTheCollection"   name="${item.itemId}">取消收藏</button>
						</div>
					</td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
		 </c:when>
		 <c:otherwise>
		 	<div class="noneTip">
		 		<img alt="收藏图片" src="${pageContext.request.contextPath}/image/ithinksimple.png" style="width:400px;height: 300px">
		 		<span class="collectionTipSpan">您没有收藏任何东西</span>
		 	</div>
		 </c:otherwise>
		</c:choose>
	</div> 
	<script type="text/javascript">
		$(function(){
			//转跳到详情页面
			$(".iwantit").click(
				function(){
					window.parent.location="http://localhost:8080/PxxySecondHand_Portal/item/showItem.do?itemId="+$(this).attr("name");
				}		
			);
			
			//取消收藏
			$(".cancelTheCollection").click(
				function(){
					var itemId = $(this).attr("name");
					$.ajax({
						async:false,
						cache:false,
						url:"../collection/changeMyCollection.do",
						data:{itemId:itemId},
						dataType:"json",
						type:"get",
						success:function(data){
							if(data.status=="200"){
								if(data.data == '0'){
									$("#del"+itemId).text("已取消");
									$("#del"+itemId).css("background","red");
								}else{
									$("#del"+itemId).text("取消收藏");
									$("#del"+itemId).css("background","#2e94d1");
								}
							}
						}
					});
				});
		});
	</script>
   <!-- S=Pager -->
   <jsp:include page="/WEB-INF/commons/paginator.jsp"></jsp:include>
   <!-- E=Pager -->
</body>
</html>
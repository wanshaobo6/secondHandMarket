<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/comment.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comment.js"></script>
<title>评价</title>
</head>
<body>
	<!-- S=maincontent -->
	<div class="mainCommentContent">
		<div class="goBack">
			<button id="goBackButton" class="goBackButton">返回上一层</button>
		</div>
		<input type="hidden" id="tradeIdInput" value="${data.id}">
		<table class="soldoutitemlist" cellspacing="0">
			<colgroup>
				<col class="clo01">
				<col class="clo02">
				<col class="clo03">
			</colgroup>
		<tbody id="39633809fe0c45f0a2c9af60e0097ecd">
				<tr class="tradeinfo">
					<td colspan="1"><span>交易编号:${data.id}</span></td>
					<fmt:formatDate value="${data.tradeCreatedTime}" pattern="yyyy-MM-dd hh-mm-ss" var="createTime"/>
					<td colspan="1"><span class="tradeCreateTimeSpan">交易时间:${createTime}</span></td>
					<td colspan="1"><span style="padding-left: 20px;" class="promptSpan">评价星级</span></td>
				</tr>
				<tr class="rowpadding">
					<td colspan="3"></td>
				</tr>
				<tr class="tradedetail">
					<td class="tradedetail_cell">
						<a class="tradeitem_pic">
							<img alt="闲置图片" src="${data.image}">
						</a>
						<div class="tradedetail_desc ">
							<p class="tradedetail_title">
								<a name="475e4355c82d47a6bcea7cfdb05a9fb9" class="itemtitle" href="javascript:void(0)">${data.itemTitle}</a>
							</p>
							<p class="tradedetail_price">
								<em>¥</em>
								<b>${data.currprice}</b>
							</p>
						</div>
					</td>
					<td class="tradedetail_status">
							<!-- S=等待评价状态 -->
							<c:if test="${data.tradeStatus eq '1'}">
							<div class="currstatus">
								 <div class="currstatus"><span class="currstatus_span">等待评价</span></div> 
								 <div class="buyerinfo">
								 	<fmt:formatDate value="${data.tradeComplishTime}" var="tradeComplishTime" pattern="yyyy-MM-dd hh:mm"/>
									<div class="buyerinfo_row"><span>交易完成时间:</span><span class="buyername">${tradeComplishTime}</span></div>
								</div>
							</div>
							</c:if>
							<!-- S=等待评价状态 -->
							<!-- S=交易结束状态 -->
						  <c:if test="${data.tradeStatus eq '2'}">
							<div class="currstatus">
								 <div class="currstatus"><span class="currstatus_span">交易完成</span></div> 
								 <div class="buyerinfo">
								 	<fmt:formatDate value="${data.tradeCEvaluateTime}" pattern="yyyy-MM-dd hh:mm:ss" var="commentTime"/>
									<div class="buyerinfo_row"><span>评论时间:</span><span class="buyername">${commentTime}</span></div>
								</div>
							 </div>
						 </c:if>
							<!-- S=交易结束状态 -->
							<!-- E=买家取消交易状态 -->
					</td>
					<td class="tradedetail_operation">
							<div class="tradedetailStartRapper">
								<c:choose>
									<c:when test="${data.tradeStatus eq '2'}">
										<span style="font-size: 15px;">已评价:</span>
									</c:when>
									<c:otherwise>
										<span style="font-size: 18px;color:red">请选择:</span>
									</c:otherwise>
								</c:choose>
								<s id="starLevel" class="start10" ></s>
								<span class="currStar">5星</span>
							</div>
					</td>
				</tr>
			</tbody>
		</table>
		<c:choose>
				<c:when test="${data.tradeStatus eq '2'}">
						<textarea class="commentContentTextarea" disabled >${data.tradeEvaluate}</textarea>
				</c:when>
				<c:otherwise>
						<textarea class="commentContentTextarea" placeholder="请输入您对这次交易的评价" ></textarea>
				</c:otherwise>
		</c:choose>
		<div class="commentButton">
			<c:choose>
				<c:when test="${data.tradeStatus eq '2'}">
					<button class="alreadyCommentButton" disabled >已评论</button>
				</c:when>
				<c:otherwise>
					<button class="commentSubmitButton">提交评论</button>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		<script type="text/javascript">
			$(function(){
				var id = ${data.tradeStatus};
				if(id==1){
					enableChooseStar()
					//鼠标点击移除选择星级事件
					$("#starLevel").click(function(){
						disableChooseStar();
					})
					//鼠标移入添加选择星级事件
					$("#starLevel").mouseenter(function(){
						enableChooseStar();
					});
					//提交评论
					$(".commentSubmitButton").click(function(){
						var tradeId = $("#tradeIdInput").val();
						if(tradeId=="")
							return ;
						var evaluateLevel = $("#starLevel").attr("class").substring(5);
						if(evaluateLevel=="")
							return ;
						if(validateComment()){
							var content = $(".commentContentTextarea").val();
							$.ajax({
								async:false,
								cache:false,
								url:"evaluateItem.do",
								data:{tradeId:tradeId,evaluateLevel:evaluateLevel,content:content},
								dataType:"json",
								type:"get",
								success:function(data){
									if(data.status==200){
										swal({ 
											  title: "漂亮！", 
											  text: "评论成功!",
											  imageUrl: "../image/thumbs-up.jpg" 
											});
										$(".commentContentTextarea").attr("disabled","true");
										$(".commentSubmitButton").removeClass().addClass("alreadyCommentButton").attr("disabled","true");
									}
								}
							});
						}
					})
				}///123456
			});
			
		</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我买到的</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/myPerchase.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
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
			<thead >
				<span>仅仅显示最近三个月已买到的数据</span>
			</thead>
			<!-- 每页6 条记录-->
			<c:forEach items="${data.itemList}" var="item">
			 <tbody id="${item.id}">
				<tr class="tradeinfo" >
					<td colspan="1"><span>交易编号:${item.id}</span></td>
					<fmt:formatDate value="${item.tradeCreatedTime}" var="createtime" pattern="yyyy-MM-dd hh:mm:ss"/>
					<td colspan="1"><span class="tradeCreateTimeSpan">交易时间:${createtime}</span></td>
					<td colspan="1"><span style="padding-left: 20px;" class="promptSpan">${item.timeLeftMessage}</span></td>
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
								<a name="${item.itemId}" class="itemtitle" href="javascript:void(0)">${item.itemTitle}</a>
							</p>
							<p class="tradedetail_price">
								<em>¥</em>
								<b>${item.currprice}</b>
							</p>
						</div>
					</td>
					<td class="tradedetail_status">
							<!-- S=交易中状态 -->
							<c:if test="${item.tradeStatus eq '0'}">
							<div class="currstatus">
								<span class="currstatus_span">交易中</span>
								<div class="buyerinfo">
									<div class="buyerinfo_row"><span>卖家姓名:</span><span class="buyername">${item.ownerName}</span></div>
									<div  class="buyerinfo_row"><span>电话:</span><span class="buyerphone">${item.phonenumber }</span></div>
								</div>
							</div>
							</c:if>
							<!--  E=交易中状态  -->
							<!-- S=等待评价状态 -->
							<c:if test="${item.tradeStatus eq '1'}">
							<div class="currstatus">
								 <div class="currstatus"><span class="currstatus_span">等待评价</span></div> 
								 <div class="buyerinfo">
								 	<fmt:formatDate value="${item.tradeComplishTime}" var="tradeComplishTime" pattern="yyyy-MM-dd hh:mm"/>
									<div class="buyerinfo_row"><span>交易完成时间:</span><span class="buyername">${tradeComplishTime}</span></div>
								</div>
							</div>
							</c:if>
							<!-- S=等待评价状态 -->
							<!-- S=交易结束状态 -->
							<c:if test="${item.tradeStatus eq '2'}">
							<div class="currstatus">
								 <div class="currstatus"><span class="currstatus_span">交易完成</span></div> 
								 <div class="buyerinfo">
								 	<fmt:formatDate value="${item.tradeCEvaluateTime}" var="tradeCEvaluateTime" pattern="yyyy-MM-dd hh:mm"/>
									<div class="buyerinfo_row"><span>评论时间:</span><span class="buyername">${tradeCEvaluateTime}</span></div>
									<div class="buyerinfo_row"><span>评价等级:</span><span class="buyername">${item.tradeEvaluateLevel}星</span></div>
								</div>
							 </div>
							</c:if>
							<!-- S=交易结束状态 -->
							<!-- S=买家交易取消状态 -->
							<c:if test="${item.tradeStatus eq '3'}">
							<div class="currstatus">
								<div class="currstatus"><span class="currstatus_span">您已取消交易</span></div>
								<fmt:formatDate value="${item.tradeCancelTime}" var="tradeCancelTime" pattern="yyyy-MM-dd hh:mm"/>
								<div class="buyerinfo_row"><span>取消时间:</span><span class="buyername">${tradeCancelTime}</span></div>
							</div>
							</c:if>
							<!-- E=买家取消交易状态 -->
							<!-- S=买家交易取消状态 -->
							<c:if test="${item.tradeStatus eq '4'}">
							<div class="currstatus">
								<div class="currstatus"><span class="currstatus_span">卖家已取消交易</span></div>
								<fmt:formatDate value="${item.tradeCancelTime}" var="tradeCancelTime" pattern="yyyy-MM-dd hh:mm"/>
								<div class="buyerinfo_row"><span>取消时间:</span><span class="buyername">${tradeCancelTime}</span></div>
							</div>
							</c:if>
							<!-- E=买家取消交易状态 -->
					</td>
					<td class="tradedetail_operation">
						<c:if test="${item.tradeStatus eq '0'}">
							<div class="tradedetail_operation_buttons">
								<button class="tradedetail_operation_button cancelTradeButton" name="${item.id}">取消交易</button>
							</div>
						</c:if>
						<c:if test="${item.tradeStatus eq '1'}">
							<div class="tradedetail_operation_buttons">
								<button class="tradedetail_operation_button commentNow" name="${item.id}">立即评价</button>  
							</div>
						</c:if>
						<c:if test="${item.tradeStatus eq '2'}">
							<div class="tradedetail_operation_buttons">
								<button class="tradedetail_operation_button seeComment" name="${item.id}">查看评价</button>  
							</div>
						</c:if>
						<c:if test="${item.tradeStatus eq '3'}">
							<div class="tradedetail_operation_buttons">
								<button class="tradedetail_operation_button deleteTradeButton" name="${item.id}">删除订单</button>  
							</div>
						</c:if>
						<c:if test="${item.tradeStatus eq '4'}">
							<div class="tradedetail_operation_buttons">
								<button class="tradedetail_operation_button deleteTradeButton" name="${item.id}">删除订单</button>  
							</div>
						</c:if>
					</td>
				</tr>
			</tbody>
			
			</c:forEach>

			</table>
		 </c:when>
		 <c:otherwise>
		 	<div class="noneTip">
		 		<img alt="收藏图片" src="${pageContext.request.contextPath}/image/ithinksimple.png" style="width:400px;height: 300px">
		 		<span class="collectionTipSpan">您没有购买过任何东西</span>
		 	</div>
		 </c:otherwise>
		</c:choose>
	</div>
	
	<script type="text/javascript">
		$(function(){
		//刷新所有的取消交易事件
		flushCancelTradeButton()
		//刷新所有的删除事件
		flushDeleteButton();
		//点击商品标题进入详情页面
		$(".itemtitle").click(
			function(){
			  window.parent.location = "/PxxySecondHand_Portal/item/showItem.do?itemId=" + $(this).attr("name");
			}		
		);
		//立即评价
		$(".commentNow").click(function(){
			 let tradeId = $(this).attr("name");
			 window.location ="/PxxySecondHand_Portal/trade/showCommentByTradeId.do?tradeId="+tradeId;
		});
		//查看评价
		$(".seeComment").click(function(){
			 let tradeId = $(this).attr("name");
			 window.location ="/PxxySecondHand_Portal/trade/showCommentByTradeId.do?tradeId="+tradeId;
		});

		});
		
		
		//改变该订单状态
		function changeStatus(tradeId,operationId){
			if(operationId=3){
				//改变状态栏
				var tradeDetailStatus =['<div class="currstatus">',
					'		<div class="currstatus"><span class="currstatus_span">您已取消交易</span></div>',
					'		<div class="buyerinfo_row"><span>取消时间:</span><span class="buyername">'+getNowFormatDate(),
					'</span></div>',
					'</div>'].join("");
				$("#"+tradeId+" .tradedetail_status").html(tradeDetailStatus);
				
				//改变按键栏
				var tradeDetailStatus =['	<div class="tradedetail_operation_buttons">',
					'		<button class="tradedetail_operation_button deleteTradeButton">删除订单</button>  ',
					'							</div>'].join("");
				$("#"+tradeId+" .tradedetail_operation").html(tradeDetailStatus);
				
				//去除待收货时间
				$("#"+tradeId+" .promptSpan").html("");
				//刷新删除按钮的事件
				flushDeleteButton();
			}
		}
		//刷新所有的删除按钮事件
		function flushDeleteButton(){
			//删除订单
			$(".deleteTradeButton").click(function(){
				swal({ 
					  title: "确定删除吗？", 
					  text: "你将无法恢复该订单", 
					  type: "warning",
					  showCancelButton: true, 
					  confirmButtonColor: "#DD6B55",
					  confirmButtonText: "确定删除！", 
					  closeOnConfirm: false
					},
					function(){
						let tradeId = $(this).attr("name");
						let pageIndex = $(".active .num").text();
						var renewUrl = "${data.URL}?keywords=${keywords}&page="+pageIndex;
						$.ajax({
							async:false,
							cache:false,
							url:"deleteTradeRecord.do",
							data:{tradeId:tradeId},
							dataType:"json",
							type:"get",
							success:function(data){
								if(data.status=="200"){
									alert("删除成功");	
									//swal("删除！", "你的订单已经被删除。", "success"); 
									window.location.href=renewUrl;
								}
							}
						}); 
					});
			 });
		}
		//刷新所有的取消交易事件
		function flushCancelTradeButton(){
			$(".cancelTradeButton").click(
					function(){
						var tradeId = $(this).attr("name");
						swal({ 
						  title: "确定取消吗？", 
						  text: "你将无法恢复该交易！", 
						  type: "warning",
						  showCancelButton: true, 
						  confirmButtonColor: "#DD6B55",
						  confirmButtonText: "确定取消！", 
						  closeOnConfirm: false
						},
						function(){
						 	$.ajax({
								async:false,
								cache:false,
								url:"cancelTheTrade.do",
								data:{tradeId:tradeId},
								dataType:"json",
								type:"get",
								success:function(data){
									if(data.status=="200"){
										changeStatus(tradeId,3);
										flushDeleteButton();
										swal("取消！", "你的交易已经被删除。", "success"); 
									}
								}
								
							}); 
						});
					
					}		
				);
		}
		//js获得当前的时间
		function getNowFormatDate(){
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var hours = date.getHours();
		    if(hours >=0 && hours <=9){
		        hours = "0" + hours;
		    }
		    var minutes = date.getMinutes();
		    if(minutes >=0 && minutes <=9){
		        minutes = "0" + minutes;
		    }
		    var seconds = date.getSeconds();
		    if(seconds >=0 && seconds <=9){
		        seconds = "0" + seconds;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + hours + seperator2 + minutes
		            + seperator2 + seconds;
		    return currentdate;
		}

	</script>
	<!-- S=Pager -->
   <jsp:include page="/WEB-INF/commons/paginator.jsp"></jsp:include>
   <!-- E=Pager -->
</body>
</html>
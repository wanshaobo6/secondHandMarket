<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/mySelled.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert2.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mySelled.js"></script>
<title>Insert title here</title>
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
				<span>仅仅显示最近三个月的数据</span>
			</thead>
			<!-- 每页6 条记录-->
			<c:forEach items="${data.itemList}" var="item">
			
			<tbody >
				<tr class="tradeinfo" >
					<td colspan="1"><span>交易编号:${item.tradeId}</span></td>
					<fmt:formatDate value="${item.tradeCreatedTime}" var="createtime" pattern="yyyy-MM-dd hh:mm:ss"/>
					<td colspan="1"><span class="tradeCreateTimeSpan">交易时间:${createtime}</span></td>
					<td colspan="1"><span style="padding-left: 20px;" class="promptSpan">${item.timeLeftMessage}</span></td>
				</tr>
				<tr class="rowpadding">
					<td colspan="3"></td>
				</tr>
				<tr class="tradedetail">
					<td class="tradedetail_cell">
						<div class="tradedetail_cell_left">
							<a class="tradeitem_pic">
								<img alt="闲置图片" src="${item.itemimage}">
							</a>
						</div>
						<div class="tradedetail_cell_right">
							<div class="tradedetail_top">
								<p class="tradedetail_title " >
									<a  href="javascript:void(0)" class="itemTitle" name="${item.itemId}">${item.itemtitle}</a>
								</p>
							</div>
							<div class="tradedetail_bottom">
								<p  class="tradedetail_price">
									<em>¥</em>
									<b class="pricebB">${item.currprice}</b>
								</p>
							<%-- 	<p class="messageNotRead">
									<b>5</b>
									<img alt="未读消息"  class="messageNotReadImg"  src="${pageContext.request.contextPath}/image/messageMyPublic.png">
								</p> --%>
							<%-- 	<p  class="tradedetail_collectionNum">
									<b>${item.collectionNum}</b>
									<img alt="收藏数" style="width: 18px;" src="${pageContext.request.contextPath}/image/collectionNum.png">
								</p> --%>
							</div>
						</div>
					</td>
					<td class="tradedetail_status">
					<!-- S=交易中状态 -->
							<c:if test="${item.tradeStatus eq '0'}">
							<div class="currstatus">
								<span class="currstatus_span">交易中</span>
								<div class="buyerinfo">
									<div class="buyerinfo_row"><span>卖家姓名:</span><span class="buyername">${item.buyerName}</span></div>
									<div  class="buyerinfo_row"><span>电话:</span><span class="buyerphone">${item.phoneNumber }</span></div>
								</div>
							</div>
							</c:if>
							<!--  E=交易中状态  -->
							<!-- S=等待评价状态 -->
							<c:if test="${item.tradeStatus eq '1'}">
							<div class="currstatus">
								 <div class="currstatus"><span class="currstatus_span">等待买家评价</span></div> 
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
						   					<c:if test="${item.tradeStatus == '0'}">
												<div class="tradedetail_operation_buttons">
													<button class="tradedetail_operation_button sellerCancelTradeBtn" name="${item.tradeId}">取消交易</button>  
													<button class="tradedetail_operation_button sellerConfirmTradeBtn" name="${item.tradeId}">面交完成</button>
												</div>
											</c:if>
											<c:if test="${item.tradeStatus == '1'}">
												<div class="tradedetail_operation_buttons">
												</div>
											</c:if>
											<c:if test="${item.tradeStatus == '2'}">
												<div class="tradedetail_operation_buttons">
													<button class="tradedetail_operation_button seeComment" name="${item.tradeId}">查看评价</button>
													<button class="tradedetail_operation_button deleteTradeBtn" name="${item.tradeId}">删除订单</button>
												</div>
											</c:if>
											<c:if test="${item.tradeStatus == '3'}">
												<div class="tradedetail_operation_buttons">
													<button class="tradedetail_operation_button deleteTradeBtn" name="${item.tradeId}">删除订单</button>
												</div>
											</c:if>
											<c:if test="${item.tradeStatus == '4'}">
												<div class="tradedetail_operation_buttons">
													<button class="tradedetail_operation_button deleteTradeBtn" name="${item.tradeId}">删除订单</button>
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
		 		<span class="collectionTipSpan">您没有售出过任何宝贝</span>
		 	</div>
		 </c:otherwise>
		</c:choose>
	</div>
    <!-- E=maincontent -->
    <script type="text/javascript">
  //刷新所有的删除按钮事件
    function flushDeleteButton(){
    	$(".deleteTradeBtn").click(function(){
    		//删除订单
    		let tradeId = $(this).attr("name");
    		let pageIndex = $(".active .num").text();
    		if(pageIndex == null)
    			pageIndex =1;
    		var renewUrl = "${data.URL}?keywords=${keywords}&page="+pageIndex;
    		Swal({
    			  title: '确定删除该订单吗?',
    			  text: "你将无法恢复订单!",
    			  type: 'warning',
    			  showCancelButton: true,
    			  confirmButtonColor: '#3085d6',
    			  cancelButtonColor: '#d33',
    			  confirmButtonText: '确定删除!'
    			}).then((result) => {
    			  if (result.value) {
    				$.ajax({
    					async:true,
    					cache:false,
    					url:"deleteTradeRecord.do",
    					data:{tradeId:tradeId},
    					dataType:"json",
    					type:"get",
    					success:function(data){
    						if(data.status=="200"){
    						 Swal(
    							      'deleted!',
    							      '删除成功.',
    							      'success'
    							    ).then(function(){
    							    	  window.location = renewUrl;
    							    })
    						}else if(data.status=="500"){
    							swal("ERROR！", data.msg,"error");
    						}
    					}
    				})
    			   
    			  }
    			})
    	 });
    }
  function confirmArrive(){
	//确定送达
		$(".sellerConfirmTradeBtn").click(function(){
			let tradeId = $(this).attr("name");
			let pageIndex = $(".active .num").text();
			if(pageIndex == null)
				pageIndex = 1;
			var renewUrl = "${data.URL}?keywords=${keywords}&page="+pageIndex;
			if(tradeId == null)
				return;
			Swal({
				  title: '确定送达?',
				  text: "你将无法恢复该交易!",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: '确定取消!'
				}).then(function(result){
				  if (result.value) {
					$.ajax({
						async:true,
						cache:false,
						url:"confirmMyTrade.do",
						data:{tradeId:tradeId},
						dataType:"json",
						type:"get",
						success:function(data){
							if(data.status=="200"){
							 Swal(
								      'Canceled!',
								      '该交易已送达.',
								      'success'
								    ).then(function(){
								    	  window.location = renewUrl;
								    })
							}else if(data.status=="500"){
								swal("ERROR！", data.msg,"error");
							}
						}
					})
				   
				  }
				})
		})
  }
    </script>
    <!-- S=Pager -->
   <jsp:include page="/WEB-INF/commons/paginator.jsp"></jsp:include>
   <!-- E=Pager -->
</body>
</html>
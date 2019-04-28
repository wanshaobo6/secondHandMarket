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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/myPublic.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert2.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myPublic.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert2.min.js"></script>
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
					<td colspan="1"><span>最后修改时间时间:${item.itemLastModefyTime}</span></td>
					<td colspan="2"><span></span></td>
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
									<a  href="javascript:void(0)" class="itemTitle" name="${item.id}">${item.itemtitle}</a>
								</p>
							</div>
							<div class="tradedetail_bottom">
								<p  class="tradedetail_price">
									<em>¥</em>
									<b class="pricebB">${item.currprice}</b>
								</p>
								<p class="messageNotRead">
									<b>${item.allMessageNums}</b>
									<img alt="全部消息"  class="messageNotReadImg" style="width: 18px;" src="${pageContext.request.contextPath}/image/messageMyPublic.png">
								</p> 
								<p  class="tradedetail_collectionNum">
									<b>${item.collectionNum}</b>
									<img alt="收藏数" style="width: 18px;" src="${pageContext.request.contextPath}/image/collectionNum.png">
								</p>
							</div>
						</div>
					</td>
					<td class="tradedetail_status">
					<!-- 当前交易的状态 0表示为等待交易  1.表示交易中  2.表示已下架(已下架的商品不能被搜索到) -->
								<div class="currstatus">
						   		  	<span class="currstatus_span"><a>${item.messagesNotReadNums}条未读留言</a></span>
						   		</div>
					</td>
					<td class="tradedetail_operation">
			   			<div class="tradedetail_operation_buttons">
			   				<!-- 1分钟只能擦亮一次 havePolishedItem-->
							<button id="polishItem${item.id}" class="tradedetail_operation_button polishItem" name="${item.id}">擦亮宝贝</button>
							<!-- 如果已经下架 按钮只有闲置上架 删除闲置 -->
							<button id="changeIsInTrade${item.id}" class="tradedetail_operation_button changeupanddown downStatus" name="${item.id}">闲置下架</button>
							<button class="tradedetail_operation_button deleteItemBtn" name="${item.id}">删除闲置</button>  
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
		 		<span class="collectionTipSpan">您没有发布任何宝贝</span>
		 	</div>
		 </c:otherwise>
		</c:choose>
	</div>
    <!-- E=maincontent -->
    <script type="text/javascript">
    	function flushDeleteItemBtn(){
    		$(".deleteItemBtn").click(function(){
        		//删除订单
        		let itemId = $(this).attr("name");
        		let pageIndex = $(".active .num").text();
        		if(pageIndex == null)
        			pageIndex =1;
        		var renewUrl = "${data.URL}?keywords=${keywords}&page="+pageIndex;
        		Swal({
        			  title: '确定删除该闲置吗?',
        			  text: "你将无法恢复此操作!",
        			  type: 'warning',
        			  showCancelButton: true,
        			  confirmButtonColor: '#3085d6',
        			  cancelButtonColor: '#d33',
        			  confirmButtonText: '确定删除!'
        			}).then(function(result){
        			  if (result.value) {
        				$.ajax({
        					async:true,
        					cache:false,
        					url:"deleteItem.do",
        					data:{itemId:itemId},
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
    </script>
    <!-- S=Pager -->
   <jsp:include page="/WEB-INF/commons/paginator.jsp"></jsp:include>
   <!-- E=Pager -->
</body>
</html>
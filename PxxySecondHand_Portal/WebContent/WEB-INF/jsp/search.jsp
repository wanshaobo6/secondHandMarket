<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/search.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/search.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/commons/top.jsp"></jsp:include>
	<!-- S=SearchResult -->
	<div class="searchResult">
		<c:choose>
			<c:when test="${data.totalCount == 0}">
				<div id="notfind">
					<p>抱歉！没有找到与<s class="keywords">${keywords}</s>相关的宝贝。</p> 
					<p><img alt="cry" src="${pageContext.request.contextPath }/image/cry.jpg"></p>
				</div>
			</c:when>
			<c:otherwise>
			<div class="sortinner">
				<ul class="sorts">
				    <li class="fl sort" name="0"><a  class="link"  title="综合排序" href="#">综合排序</a></li>
					<li class="fl sort" name="1"><a  class="link"  title="最新" href="#">最新擦亮</a></li>
					<li class="fl sort" name="2"><a class="link"  title="收藏量" href="#">收藏量</a></li>
					<li class="fl sort" name="3"><a class="link"  title="信用" href="#">信用</a></li>
					<li class="fl sort"><a class="link"  title="价格" href="#">价格</a>
						<ul>
							<li class="sort" name="4"><a class="link"  title="价格从低到高" href="#">价格从低到高</a></li>
							<li class="sort" name="5"><a class="link"  title="价格从高到低" href="#">价格从高到低</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="searchlist">
					<c:forEach items="${data.itemList}" var="item">
						<div class="searchitem">
						<div class="searchitem_image fl">
							<a>
								<c:forTokens var="image" begin="0" end="0" items="${item.itemimages}" delims=",">
										<img alt="商品图片" src="${image}">
								</c:forTokens>
							</a>
						</div>
						<div class="search_center fl">
							<div class="searchitem_title ">
								<p><a href="${pageContext.request.contextPath}/item/showItem.do?itemId=${item.id}">${item.itemtitle}</a></p>
							</div>
						</div>
						<div class="searchitem_price fl">
							<div>
	     						 <span class="price g_price g_price-highlight">
	      						  <span>¥</span><strong>${item.currprice}</strong>
	     						 </span>
	       				 <span class="ship icon-service-free"></span>
	 				   		 </div>
						</div>
						<div class="searchitem_desc fl">
							<ul>
								<li><p>收藏量:</p><p class="updatetime">${item.collectionNum}</p></li>
								<li><p>发布时间:</p><p class="updatetime">${item.polishTimeImprecise}</p></li>
								<li><p>买家信誉积分:</p><p class="updatetime">${item.credit}</p></li>
								<li><p>浏览量:</p><p class="updatetime">${item.scanNum}</p></li>
							</ul>
						</div>
					</div>
					</c:forEach>
				</div>
			</div> 
		</div>
		<!-- E=SearchResult -->
		
		<!-- S=division  -->
		<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
		<!-- E=division  -->
		
		<!-- S=Pager -->
		  <div class="pager" id="bottom_pager">
			<div class="pagin">
			      <ul class="items fl">
			      	<%--当前如果是第一页则第一页按钮失效 --%>
			          <c:choose>
			          	<c:when test="${data.currentPage != 1}">
			          		<li class="item prev prev-disabled">
				             <span class="num" >
				              <span ></span>
				              <span class="icon ">上一页</span>
				            </span>
				       	   </li>
			          	</c:when>
			          	<c:otherwise>
			          		<li class="item prev">
				             <span class="num" >
				              <span ></span>
				              <span class="icon  btnDisabled">上一页</span>
				            </span>
				       	   </li>
			          	</c:otherwise>
			          </c:choose>
			        
			          <c:choose>
			          	<%--如果总页数小于等于5则设置开始页和结束页为1 totlepage --%>
			          	<c:when test="${data.totalPage <= 6}">
			          		<c:set var="begin" value="1"></c:set>
			          		<c:set var="end" value="${data.totalPage}"></c:set>
			          	</c:when>
			          	<%--如果总页数大于5--%>
			          	<c:otherwise>
			          		<c:set var="begin" value="${data.currentPage-2}"></c:set>
			          		<c:set var="end" value="${data.currentPage+3}"></c:set>
			          		 <c:choose>
			          		 	<c:when test="${begin<1}">
			          		 		<c:set var="begin" value="1"></c:set>
			          				<c:set var="end" value="5"></c:set>
			          		 	</c:when>
			          		 	<c:otherwise>
				          		 	 <c:when test="${end > data.totalPage}">
				          		 		<c:set var="begin" value="${data.totalPage-5}"></c:set>
				          				<c:set var="end" value="${data.totalPage}"></c:set>
				          		 	</c:when>
			          		 	</c:otherwise>
			          		 </c:choose>
			          	</c:otherwise>
			          </c:choose>
			          
			          <%--显示页码 --%>
					<c:forEach begin="${begin}" end="${end}" var="i">
					 <c:choose>
					 	<c:when test="${i eq data.currentPage}">
					 		 <li class="item active">
				              <span class="num">${i}</span>
				            </li>
					 	</c:when>
					 	<c:otherwise>
					 		<li class="item">
			                <a style="display:inline-block;width:100%;height:100%" class="J_Ajax num" href="${pageContext.request.contextPath}/search/searchByKeywords.do?keywords=${keywords}&page=${i}&orderCondition=${data.orderCondition}" >${i}</a>
			              </li>
					 	</c:otherwise>
					 </c:choose>	
					</c:forEach>	
					
					<%--判断是否要打点 --%>	
					          
					<c:if test="${end !=  data.totalPage}">
						<li class="item dot">...</li>
					</c:if>
					<%--判断第一页是否失效 --%>
					<c:choose>
						<c:when test="${data.currentPage < end}">
							<li class="item next">
					            <a style="display:inline-block;width:100%;height:100%" class="J_Ajax num icon-tag" href="${pageContext.request.contextPath}/search/searchByKeywords.do?keywords=${keywords}&page=${data.currentPage+1}&orderCondition=${data.orderCondition}" >
					              <span>下一页</span>
					              <span class="icon icon-btn-next-2"></span>
					            </a>
				          </li>
						</c:when>
						<c:otherwise>
						  <li class="item next">
				            <a style="display:inline-block;width:100%;height:100%" class="J_Ajax num icon-tag" href="#" >
				              <span  class="btnDisabled">下一页</span>
				              <span class="icon icon-btn-next-2"></span>
				            </a>
				          </li>
						</c:otherwise>
					</c:choose>
			      </ul>
	    	 <div class="total fl">
	     		   共 ${data.totalPage} 页，
	      	</div>
	      <div class="pageform fl">
		        <span class="text">到第</span>
		        <input class="pageinput J_Input" type="number" value="${data.currentPage}" min="1" max="100" aria-label="页码输入框">
		        <span class="text">页</span>
		        <span class="btn J_Submit" role="button" tabindex="0">确定</span>
	      </div>
	    </div>
		</div>  
		<!-- E=Pager -->
			
			</c:otherwise>
		</c:choose>
		
		<%-- --%>
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<!-- E=division  -->
	
	<!-- S=coptright -->
		<div class="copyright">
			<table width="1000" cellspacing="0" cellpadding="0" border="0" align="center">
   			 <tbody><tr>
       			 <td style="font-size:13px;" valign="top" height="113" align="center">
        			  <div style="height:15px;"></div>
          			  <font style="color:#59598F; font-size:13px; ">版权：</font><a id="HyperLink23" target="_blank"><font style="color:#59598F; font-size:13px;">萍乡学院跳蚤市场</font></a>&nbsp;
           			     <font style="color:#59598F; font-size:13px; ">地址：江西省萍乡市萍安北大道211号&nbsp;邮编：12345 &nbsp;联系电话：1234567 传真：12345</font>
             		   &nbsp;<font style="color:#59598F; font-size:13px; ">备案序号：</font>
            		    <a href="http://www.miibeian.gov.cn/" target="_blank"><font style="color:#59598F; font-size:13px;">赣ICP备1234567号</font>
             		   </a>
              		  <br>
         		 	 <a href="http://59.53.216.180:8080/jxeduStat/login?id=jxedu@028" target="_blank">
                		<font color="white">[JXEDUSTATS]</font>
                	</a>
        		</td>
    		</tr>
		</tbody>
		</table>
		</div>
	<!-- E=coptright -->
	
	<!-- S=script  -->
	<script type="text/javascript">
		$(function(){
			var keywords = "${keywords}";
			var orderCondition = ${data.orderCondition};
			var currpath = "${pageContext.request.contextPath}";
			//渲染排序方式
			$(".sort").each(function(){
				var currOrder = $(this).attr("name");
				if(currOrder == null)
					return;
				if(orderCondition == currOrder)
					$(this).addClass("selectdOrderType").removeClass("sort");
			})
			//排序方式的点击时间
			$(".sort").click(function(){
				var currOrder = $(this).attr("name");
				window.location.href=currpath+"/search/searchByKeywords.do?keywords=${keywords}&orderCondition="+currOrder+"";
			})
		})
	</script>
	<!-- E=script  -->
</body>
</html>
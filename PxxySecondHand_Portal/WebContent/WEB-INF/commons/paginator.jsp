<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/paginator.css"/>
</head>
<body>
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
				              <span class="icon icon-btn-prev-2-disable"><a href="${data.URL}?keywords=${keywords}&page=${i}">上一页</a></span>
				            </span>
				       	   </li>
			          	</c:when>
			          	<c:otherwise>
			          		<li class="item prev prev-disabled">
				             <span class="num" >
				              <span ></span>
				              <span class="icon btnDisabled">上一页</span>
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
			                <a style="display:inline-block;width:100%;height:100%" class="J_Ajax num" href="${data.URL}?keywords=${keywords}&page=${i}" >${i}</a>
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
					            <a style="display:inline-block;width:100%;height:100%" class="J_Ajax num icon-tag" href="${data.URL}?keywords=${keywords}&page=${data.currentPage+1}" >
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
</body>
</html>
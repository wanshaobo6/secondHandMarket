<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
  <div id="${childItem.id}" class="reply${layerNum} child_comment searchCommentId" >
         <span class="reply_name to_name">${childItem.fromusername}</span>回复<span class="reply_name">${childItem.tousername}</span>：<span class="reply_content">${childItem.content} </span> 
         <a  data-id="1" class="del_reply" > 
         	 <span name="${childItem.id}" class="reply_he" style="margin-left: 2px;">回复</span>
             <i class="icon del_comment" name="${childItem.fromuserid}" >删除</i>
         	 <span style="margin-left: 2px;">${childItem.commentTime}</span>
         </a>   
         <c:if test="${not empty childItem.itemDescComments}">  
                   <c:forEach var="childItem" items="${childItem.itemDescComments}">
                   		<c:set var="layerNum" value="${layerNum+1}" scope="session"></c:set>   
                       <c:set var="childItem" value="${childItem}" scope="request"/>  
                 	  <jsp:include page="recursive.jsp"/>  
                   </c:forEach>              
         </c:if>  
   </div> 
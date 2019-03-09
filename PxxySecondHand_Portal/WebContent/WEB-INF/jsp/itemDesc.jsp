<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物品描述</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/itemDesc.css"/>
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/itemDesc.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/commons/top.jsp" ></jsp:include>
	
	<!-- S=mainContent -->
	<div class="mainContent">
		<div class="idle_top">
			<div class="imageContent_wapper fl">
				<%--显示大图 --%>
				<c:forTokens var="item" begin="0" end="0"  items="${data.itemimages}" delims=",">
						<div class="imageContent">
							<img id="mediumImg" src="${item}">
						</div>
				</c:forTokens>
				<div id="smallimg">
				<ul id="icon.list">
					<%--显示小图 --%>
					<c:forTokens var="item" begin="0" end="4" items="${data.itemimages}" delims=",">
						<li><img src="${item}"/></li>
					</c:forTokens>
				</ul>
				</div>
			</div>
			
			<div class="property_wapper fr">
				<div class="idle_property_title title">
					<s></s><strong>宝贝属性</strong>
				</div>
				<div class="property">
					<input type="hidden" id="itemId" value="${data.id}"/>
					<h1 class="property_title">${data.itemtitle}</h1>
					 <ul class="price-info">
		      		    <li class="price-block">
				            <span class="para" >转&nbsp;&nbsp;卖&nbsp;&nbsp;价：</span>
				            <span class="price big" ><b style="font-size: 30px;color:red">¥</b><em style="font-size: 35px;">${data.currprice}</em></span>
		    		   </li>
		    		    <li class="price-block">
				            <span class="para" >购入价：</span>
				            <span class="price big" style="text-decoration:line-through;font-size: 20px;color:red;"><b>¥</b>${data.formerprice}</span>
		    		   </li>
				    </ul>
				    <ul class="idle-info">
				    	<li class="idle-info-item">
				    		<span>买家称呼:</span><span>万先生</span>
				    	</li>
				    	<li class="idle-info-item">
				    		<span>买家信用:</span><span>${data.credit}星</span>
				    	</li>
				    	<li class="idle-info-item">
				    		<span>被收藏次数:</span><span>${data.collectionNum}</span>
				    	</li>
				    	<li class="idle-info-item">
				    		<c:choose>
				    			<c:when test="${data.icondition}==10">
				    				<span>成色:</span><span>全新</span>
				    			</c:when>
				    			<c:otherwise>
				    				<span>成色:</span><span>${data.icondition}成新</span>
				    			</c:otherwise>
				    		</c:choose>
				    	</li>
				    	<li class="idle-info-item">
				    		<fmt:formatDate value="${data.updated}" pattern="yyyy-MM-dd HH" var="latestEditTime"/>
				    		<span>最近更新时间:</span><span>${data.time}</span>
				    	</li>
				    </ul>
				    <c:choose>
				    	<c:when test="${data.isInTrade  ne 0}">
				    		<c:choose>
				    			<c:when test="${data.isInTrade eq 1}">
				    				 <div class="cannt_buy">
				    					<a class="btn">已售出</a>
				 			 	 	 </div>
				    			</c:when>
				    			<c:otherwise>
				    			<div class="cannt_buy">
				    				<a class="btn">已下架</a>
				 			 	  </div>
				    			</c:otherwise>
				    		</c:choose>
				    	</c:when>
				    	<c:otherwise>
				    			<c:choose>
				    				<c:when test="${not empty user && user.id eq data.userId}">
				    					<div class="cannt_buy">
					    					<a class="btn" style="backgroud-color:'red'">等待购买</a>
					 			  		 </div>
				    				</c:when>
				    				<c:otherwise>
				    					<div class="order-now">
					    					<a class="btn">立即预定</a>
					 			  		 </div>
				    				</c:otherwise>
				    			</c:choose>
				    	</c:otherwise>
				    </c:choose>
				
				    <div class="do-more">
				    	<div class="addcollection">
				    			<c:choose>
				    				<c:when test="${data.isCollected eq '1'}">
				    					<span class="collectionTip" style="color:red">go into action---></span>
				    					<img class="doCollectionImg"  alt="收藏" src="${pageContext.request.contextPath}/image/staron.png"/>
				    				</c:when>
				    				<c:otherwise>
				    					<span class="collectionTip" style="color:red">喜欢我就点收藏吧---></span>
				    					<img class="doCollectionImg"  alt="收藏" src="${pageContext.request.contextPath}/image/starno.png"/>
				    				</c:otherwise>
				    			</c:choose>
				    	</div>
				    </div>
				</div>
			</div>
		</div>
		<div class="idle_foot">
			<div class="idle_message">
				<div class="idle_message_title title">
					<s></s><strong>宝贝留言</strong>
				</div>
				<div class="idle_message_content">
					<div class="idle_message_content_wapper">
						<!-- S=留言部分 -->
				     		   <h3 style="text-indent: 2em;">留言列表</h3>
				     			 <hr>
							<div class="comment_list">
						     			 
					     	 	<c:choose>
									<c:when test="${not empty comments}">
										<c:forEach items="${comments}" var="item">
										 <div id="${item.id}" class="searchCommentId comment reply0">
										 <!-- 头像 目前没有 -->
<!-- 								             <div class="imgdiv">
								                <img class="imgcss"  src="./images/user.jpg"/>
								             </div>
 -->								         <div class="conmment_details">
								                 <div style="float:left;">
								                  <span class="comment_name to_name">${item.fromusername} </span>     <span>${item.commentTime}</span>
								                </div>
								                <div class="del">
								                  <span  class="reply_he" name="${item.id}">回复</span>
								                  <span  class="show_reply_list">查看回复</span>
								                  <a  > <i class="icon del_comment" name="${item.fromuserid}">删除</i></a> 
								                </div>
								                 <div class="comment_content" > ${item.content}
								                </div>
								            </div>
								           <div class="reply_list"   style="display:inline-block;">   <!--style="display:none;"-->
								           		<c:forEach var="childItem" items="${item.itemDescComments}"> 
								           						<c:set var="layerNum" value="1" /> 
										                		 <%@include file="recursive.jsp"%>   
                     						    </c:forEach>    
								           </div> 
								          </div>
										</c:forEach>
										
									</c:when>	
									<c:otherwise>
										<div class="no_comment">
											<img  alt="无评论" src="${pageContext.request.contextPath}/image/leaveMessage.png">
										</div>
									</c:otherwise>	     	 	
					     	 	</c:choose>
					     	 															        
	     			  </div>
						       <!--回复列表结束-->
										<h3 style="text-indent: 2em;">给我留言</h3>
						     			 <hr>
						       <div class="comment_add_or_last" >
						       		<a id="comment_tip" style="color:red"></a>
						            <textarea id="commentContent" class="smohan_text"></textarea>
						            <button class="addCommentButton" >发表评论</button>
						       </div> 
						       </div> 
						       <hr>
						     	<!--E=留言部分 -->
				 </div>
			 </div>
		  </div>
		<div class="idle_desc">
				<div class="idle_desc_title title">
					<s></s><strong>宝贝介绍</strong>
				</div>
				<div class="idle_desc_content">
					<div class="idle_desc_content_wapper">
						<center>${data.itemdesc}</center>
					</div>
				</div>
		 </div>
	</div>
	<!-- S=mainContent -->
	
	<!-- S=copyright -->
	<jsp:include page="/WEB-INF/commons/copyright.jsp"></jsp:include>
	<!-- E=copyright -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>萍院跳蚤市场</title>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert2.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vue.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/commons/top.jsp"></jsp:include>
	<!-- S=Nav -->
	<div class="navbar">
		<ul class="menu fl">
			<li class="fl">
				<a id="indexli" href="javascript:void(0)">首&nbsp;&nbsp;&nbsp;页</a>
			</li>
			   		<li  class="fl showAfterLoginIn">
						<a href="${pageContext.request.contextPath}/userCenter.html">用户中心</a>
					</li>
					<li  class="fl showAfterLoginIn">
						<a href="${pageContext.request.contextPath}/publicItem.html">发布闲置</a>
					</li>
					<li  class="fl showAfterLoginIn">
						<a href="#">我的闲置</a>
						<ul>
							<li><a href="#">出售中</a></li>
							<li><a href="#">交易中</a></li>
						</ul>
					</li>
					<li class="fl showAfterLoginIn">
						<a href="#">消息(0)</a>
					</li>
		</ul>
	</div>
	<!-- E=Nav -->
	
	<!-- S=newMessage -->
		<div class="newMessage">
			<div class="carousel fl" >
				<!-- S=轮播图  -->
				<div id="myCarousel" class="carousel slide">
					<!-- 轮播（Carousel）指标 -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>   
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner">
						<div class="item active">
							<img src="${pageContext.request.contextPath}/image/1.jpg" style="width:725px;height:255px" alt="First slide">
							<div class="carousel-caption">标题 1</div>
						</div>
						<div class="item">
							<img src="${pageContext.request.contextPath}/image/1.jpg" style="width:725px;height:255px" alt="Second slide">
							<div class="carousel-caption">标题 2</div>
						</div>
						<div class="item">
							<img src="${pageContext.request.contextPath}/image/2.jpg" style="width:725px;height:255px" alt="Third slide">
							<div class="carousel-caption">标题 3</div>
						</div>
					</div>
					<!-- 轮播（Carousel）导航 -->
					<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					</a>
				</div> 
			<!-- E=轮播图  -->
			</div>
			<div class="websitenews fr">
						<div class="websitenews_title title">
	  						<s class="websitenews_title_icon"> </s><strong>网站动态</strong>
	  					</div>
	  					<div class="websitenews_content">
	  						 <ul>
	  					<c:choose>
	  						<c:when test="${!empty messageList}">
	  							<c:forEach items="${messageList}" var="message">
	  								<c:choose>
	  									<c:when test="${message.toone eq '1'}">
	  										<li><span class="dynamic_message"><s class="private_message_s"></s></span><a href="${pageContext.request.contextPath}/message/getMessageById.html?messageId=${message.id}">${message.title}</a><span class="publicMessageTime">${message.createTimeImPrecise}</span></li>
	  									</c:when>
	  									<c:otherwise>
	  										<li><span class="dynamic_message"><s class="dynamic_message_s"></s></span><a href="${pageContext.request.contextPath}/message/getMessageById.html?messageId=${message.id}">${message.title}</a><span class="publicMessageTime">${message.createTimeImPrecise}</span></li>
	  									</c:otherwise>
	  								</c:choose>
	  							</c:forEach>
	  						</c:when>
	  						<c:otherwise>
		  						<div class="nofucksay">
		  						</div>
	  						</c:otherwise>
	  					</c:choose>
	  						</ul> 
	  					</div>
			</div>
		</div>
	<!-- E=newMessage -->
	
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<!-- E=division  -->
	
	<!-- S=Content -->
	<div class="content">
		<div class="c_catalog fl">
			<div class="catalog_title title">
	  			<s class="catalog_title_icon"></s><strong>物品分类</strong>
	  			<a class="fr" @mouseenter="showAllCategory = true" @mouseleave="showAllCategory = false">更多>></a>
	  		</div>
	  		<!-- S = 分类体 -->
  			<div class="catalog_body" id="catagory_body" >
  				<div class="curr_child_category_box" v-show="showCurrChildBox" v-on:mouseenter="showCurrChildBox = true" v-on:mouseleave="showCurrChildBox = false">
  					 <span class="main-link" style="padding:10px">
				          <a href="//s.2.taobao.com/list/list.htm?spm=a2170.1841804.1867087.2&amp;catid=50100398&amp;st_trust=1&amp;ist=1" target="_blank" v-for="cat in selectedItemCat.itemCat" v-text="cat.categoryname">
				          </a>
				       </span>
  				</div>
  				
 				<ul class="parent_category">
  				 		 <li class="child_category" v-for="(itemCat,index) in itemCats" @key="index" v-show="index<6"   v-on:mouseenter="mouseinCurrChildDialog(index)"  v-on:mouseleave="mouseoutCurrChildDialog(index)" >
						      <div class="item_box" >
						        <b>  </b>
						        <a href="//s.2.taobao.com/list/list.htm?spm=a2170.1841804.1867087.1&amp;catid=50100398&amp;st_trust=1&amp;ist=0"   target="_blank" >
						       	  {{itemCat.categoryname}}
						        </a>
						        <span class="main-link">
						          	<a href="//s.2.taobao.com/list/list.htm?spm=a2170.1841804.1867087.2&amp;catid=50100398&amp;st_trust=1&amp;ist=1" target="_blank" v-for="(childItemCat,i) in itemCat.itemCat" @key="i" v-show="i<3">
						          	  {{childItemCat.categoryname}}
						          	</a>
						        </span>
						      </div>
  				 		 </li>
				</ul>
  			</div>
  				<!-- E = 分类体 -->
	  	</div>
	  	
  		<div class="newestpublic fl">
			<div class="newestpublic_title title">
				<ul>
					<li><s></s><strong>校园最新发布</strong></li>
					<li><strong></strong></li>
				</ul>
			</div>
			<div class="newestpublic_content">
			<div class="newest_sell">
					<ul class="ultable">
						<c:forEach items="${newestPublics}" var="newestPublic">
							<li>
	  							<div>
	  								<a href="${pageContext.request.contextPath}/item/showItem.do?itemId=${newestPublic.itemId}"><span class="newestPublicTitle">${newestPublic.itemTilte}</span><span class="newestPublicTime">${newestPublic.lastPublicTime}擦亮</span></a>
	  							</div>
	  						</li>
						</c:forEach>
	  				</ul>
				</div>
				<div class="newest_needed">
					<ul class="ultable">
	  						<li>
	  							<div>
	  								<a href="#">园区回收手表回收二手闲置手表价格  1秒前发布</a>
	  							</div>
	  						</li>
	  							<li>
	  							<div>
	  								<a href="#">园区回收手表回收二手闲置手表价格  &nbsp &nbsp &nbsp &nbsp 1秒前发布</a>
	  							</div>
	  						</li>	<li>
	  							<div>
	  								<a href="#">园区回收手表回收二手闲置手表价格 &nbsp &nbsp &nbsp &nbsp   1秒前发布</a>
	  							</div>
	  						</li>	<li>
	  							<div>
	  								<a href="#">园区回收手表回收二手闲置手表价格  &nbsp &nbsp &nbsp &nbsp    1秒前发布</a>
	  							</div>
	  						</li>	<li>
	  							<div>
	  								<a href="#">园区回收手表回收二手闲置手表价格  &nbsp &nbsp &nbsp &nbsp  1秒前发布</a>
	  							</div>
	  						</li>	<li>
	  							<div>
	  								<a href="#">园区回收手表回收二手闲置手表价格                1秒前发布</a>
	  							</div>
	  						</li>
	  				</ul>
				</div>
			</div>
  		</div>
  			
	  	<div class="advice fr">
	  		<div class="advice_title title">
	  			<s></s><strong>站长信箱</strong>
	  		</div>
	  		<div class="advice_content">
	  			<span>请留下您宝贵的建议:</span>
	  			<textarea id="adviceContent" style="width:220px;height:200px"></textarea>
	  			<button class="advicebutton">发送</button>
	  		</div>
	  	</div>
	</div>
	<!-- E=Content -->
	
	
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<!-- E=division  -->
	
	<!-- S=GuessYouLike -->
	<div class="guessyoulike">
		<div class="guessyoulike_title title">
			<s></s><strong>你可能喜欢</strong>
		</div>
		<div class="guessyoulike_content">
			<div class="guessitemtr">
				<c:forEach begin="0" end="5" items="${guessList}" var="guessItem">
					<div class="guessitemtd"><a href="${pageContext.request.contextPath}/item/showItem.do?itemId=${guessItem.itemId}"> <img alt="猜你喜欢" src="${guessItem.image}"></a><span><a href="${pageContext.request.contextPath}/item/showItem.do?itemId=${guessItem.itemId}">${guessItem.title}</a></span></div>
				</c:forEach>
			</div>
			<div class="guessitemtr">
				<c:forEach begin="6" end="11" items="${guessList}" var="guessItem" >
				<div class="guessitemtd"><a href="${pageContext.request.contextPath}/item/showItem.do?itemId=${guessItem.itemId}"> <img alt="猜你喜欢" src="${guessItem.image}"></a><span><a href="${pageContext.request.contextPath}/item/showItem.do?itemId=${guessItem.itemId}">${guessItem.title}</a></span></div>
					<!-- <div class="guessitemtd"> <img alt="猜你喜欢" src="image/slide1.png"><span><a href="#">商品的标题</a></span></div> -->
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- E=GuessYouLike -->
	
	<!-- S = ShowAllCategory -->
		<div class="allCategoriesPan">
			<div class="tbg" id=all_catagory >所有类目</div>
			<div class="content" id="location" >
			<div class="fenlei" id="baihuo">	
		<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei">	
			<dt>
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	                        <br />                          </dt>
	                <dd class="marginLeft">
	                  <a target="_blank" href="/chuangdian/">床</a>
	                      <a target="_blank" href="/guizi/">柜子</a>
	                   <a target="_blank" href="/zhuoyi/">桌椅</a>
	                <a target="_blank" href="/shafachaji/">沙发</a>
	                  <a target="_blank" href="/zixingchemaimai/">自行车</a>
	                  <a target="_blank" href="/diandongche/">电动车</a>
	                     <a target="_blank" href="/motuoche/">摩托车</a>
	                     </dd>
	            </dl>
	        </div>
	        <div class="fenlei2 fl">	
			
	           <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
	            <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
	        </div>             
      		  <div class="fenlei3 fl">	
         		  <a  target="_blank" href="/jiaju/" class="titlefont">二手家具</a>
          		  <a style="padding-left: 20px;" target="_blank" href="/rirongbaihuo/" class="titlefont">家居百货</a>
       		 </div>
       	 </div>
		</div>
	<!-- E = ShowAllCategory -->
	
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<!-- E=division  -->
	
	<!-- S=foot -->
	<div class="foot">
		<div class="link">
            <tbody><tr>
                <td style="font-size: 12px;" valign="top" height="200" background="images/new_index_r6_c1.jpg" align="center">
                    
                    <table width="1000" cellspacing="0" cellpadding="0" border="0" align="center">
                        <tbody><tr>
                            <td style="width:38%;" valign="top" align="left">
                            
                            <div style="height:30px;"></div>
                     	  <table id="DataList8" style="width:100%;border-collapse:collapse;" cellspacing="0" border="0">
								<tbody><tr>
											<td>
                                                    <table style="width: 100%;" cellspacing="0" cellpadding="0" border="0" align="center">
                                                        <tbody>
                                                        <tr>
                                                           <img alt="" style="width:360px;height: 170x;" src="${pageContext.request.contextPath}/image/bigpig.jpg">
                                                        </tr>
                                                 	   </tbody>
                                                    </table>
                                                </td>
										</tr>
							   </tbody>
					 	</table>
                                            
                            
                            
                            </td>
                             <td style="width:30%;" valign="top">
                             
                              <div style="height: 30px;">
                                </div>
                                <table style="width: 100%;">
                                    <tbody><tr>
                                        <td align="left">
                                            <font style="color:#686399; font-size:16px; font-weight:bold;">快速通道</font>
                                            <div style="height: 15px;"> </div>
                                        
                                        </td>
                                        
                                    </tr>
                                    <tr>
                                         <td align="left">
                                            <a href="${pageContext.request.contextPath }/admin/AdminLogin.html" target="_blank"><font style="color:#686399; font-size:12px; ">后台管理</font></a>
                                            
                                            
                                            &nbsp;
    <a href="http://portal.pxc.jx.cn/" target="_blank"><font style="color:#686399; font-size:12px; ">智慧校园入口</font></a>
                                            
                                            
                                            &nbsp;
                                             <a href="http://117.40.229.8" target="_blank"><font style="color:#686399; font-size:12px; ">
                                           教务系统</font></a>
                                            
                                            &nbsp;<a href="http://www.pxc.jx.cn/viewlist5-1.htm" target="_blank"><font style="color:#686399; font-size:12px; ">
                                           专题</font></a>
                                            
                                            
                                            &nbsp;<font style="color:#686399; font-size:12px; ">
                                            微信微博</font>
                                            
                                            <div style="height: 15px;"> </div>
                                        
                                        </td>
                                        
                                    </tr>
                                    <tr><td align="left">
                                            <font style="color:#686399; font-size:16px; font-weight:bold;">友情链接</font>
                                            <div style="height: 15px;"> </div>
                                        
                                        </td>
                                    </tr><tr>
                                         <td align="left">
                                             <select name="selFrnd3" style="width: 140px;" onchange="javascript:window.open(this.options[this.selectedIndex].value);">
                                                 <option selected="selected" value="http://www.ncu.edu.cn/">萍乡学院</option>
                                             </select>
                                             
                                             <select name="selFrnd2" style="width: 140px;" onchange="javascript:window.open(this.options[this.selectedIndex].value);">
                                                 <option selected="selected">政府部门</option>
                                                 <option value="http://www.moe.gov.cn/">国家教育部</option>
                                                 <option value="http://www.most.gov.cn/">国家科技部</option>
                                                 <option value="http://program.most.gov.cn/">国家科技计划申报中心</option>
                                                 <option value="http://www.nsfc.gov.cn/">国家自然科学基金委</option>
                                                 <option value="http://www.npopss-cn.gov.cn/">全国哲学社科办</option>
                                                 <option value="http://www.jxedu.gov.cn/">江西省教育厅-江西教育网</option>
                                                 <option value="http://www.jiangxi.gov.cn/">江西省政府</option>
                                                 <option value="http://www.pingxiang.gov.cn/">萍乡市政府</option>
                                                 <option value="http://www.cern.net.cn/">中国教育资源网</option>
                                                 <option value="http://www.chinaedu.edu.cn/">中国教育信息网</option>     
                                                 <option value="http://sub.pxc.jx.cn:8013">江西省软科学基地</option>                                             
                                             </select>                                       
                                        </td>
                                        
                                    </tr>
                                </tbody></table>
                             
                            </td>
                            <td style="width: 32%;" valign="top">
                                <div style="height: 30px;">
                                </div>
                                <table style="width: 100%;">
                                    <tbody>
									  <tr>
                                        <td>
                                        
                                       <a href="http://xyw.pxc.jx.cn/" target="_blank"><img src="${pageContext.request.contextPath}/image/smallpig.jpg" width="143px" height="48px" border="0"></a>
                                        </td>
                                        <td>
                                      
                                        <a href="http://xyw.pxc.jx.cn/" target="_blank"><img src="${pageContext.request.contextPath}/image/smallpig.jpg" width="143px" height="48px" border="0"></a>
                                        
                                        </td>
                                    </tr>  <tr>
                                        <td>
                                        
                                       <a href="http://xyw.pxc.jx.cn/" target="_blank"><img src="${pageContext.request.contextPath}/image/smallpig.jpg" width="143px" height="48px" border="0"></a>
                                        </td>
                                        <td>
                                      
                                        <a href="http://xyw.pxc.jx.cn/" target="_blank"><img src="${pageContext.request.contextPath}/image/smallpig.jpg" width="143px" height="48px" border="0"></a>
                                        
                                        </td>
                                    </tr>
                                     <tr>
                                        <td>
                                        
                                       <a href="http://xyw.pxc.jx.cn/" target="_blank"><img src="${pageContext.request.contextPath}/image/smallpig.jpg" width="143px" height="48px" border="0"></a>
                                        </td>
                                        <td>
                                      
                                        <a href="http://xyw.pxc.jx.cn/" target="_blank"><img src="${pageContext.request.contextPath}/image/smallpig.jpg" width="143px" height="48px" border="0"></a>
                                        
                                        </td>
                                    </tr>
                                </tbody></table>
                            </td>
                        </tr>
                    </tbody></table>
                </td>
            </tr>
        </tbody></table>
		</div>
		
	</div>
	<!-- E=foot -->
	
		
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
	
	<script type="text/javascript">
		$(function(){
		        // 初始化轮播
		        $(".start-slide").click(function(){
		            $("#myCarousel").carousel('cycle');
		        });
		        // 停止轮播
		        $(".pause-slide").click(function(){
		            $("#myCarousel").carousel('pause');
		        });
		        // 循环轮播到上一个项目
		        $(".prev-slide").click(function(){
		            $("#myCarousel").carousel('prev');
		        });
		        // 循环轮播到下一个项目
		        $(".next-slide").click(function(){
		            $("#myCarousel").carousel('next');
		        });
		        // 循环轮播到某个特定的帧 
		        $(".slide-one").click(function(){
		            $("#myCarousel").carousel(0);
		        });
		        $(".slide-two").click(function(){
		            $("#myCarousel").carousel(1);
		        });
		        $(".slide-three").click(function(){
		            $("#myCarousel").carousel(2);
		        });
		        
		    });
	</script>
	<script type="text/javascript">
		var categoryList = new Vue({
			el:"#catagory_body",
			data:{
				itemCats:[],
				showCurrChildBox:false,
				selectedItemCat:{},
				
			},
			methods:{
				mouseinCurrChildDialog(index){
					this.showCurrChildBox = true;
					this.selectedItemCat = this.itemCats[index];
					document.getElementsByClassName("curr_child_category_box")[0].style.top=(index==0?15:45*index+15)+"px";
				},
				mouseoutCurrChildDialog(index){
					this.showCurrChildBox = false;
				},
				
			},
			created(){
				 $.ajax({
						async:false,
						cache:false,
						url:"itemcat/queryAllCatgories.do",
						type:"get",
						success:(data) => {
							this.itemCats = data;
							this.selectedItemCat = this.itemCats[0];
						}
					});
			}
		  });
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布商品</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/publicItem.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css">
<link rel="styleSheet" href="${pageContext.request.contextPath}/css/sweetalert.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/publicItem.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/commons/top.jsp"></jsp:include>
	<!--S=SubmitForm -->
	<div class="formwapper">
		<div class="title">
			<s></s><strong>发布闲置</strong>
		</div>
		<form id="itemAddForm" action="">
			<div class="block_wapper">
				<div class="block_title">
					<h2>基本信息</h2>
				</div>
				<div class="block_content">
					<div class="rows_wapper">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								一级类别
							</span>
						</div>
						<div class="rows_content fr">
							<div class="selectordef">
								<select class="selectBox firstLevelCatSelect">
								</select>
							</div>
						</div>
					</div>
					
						<div class="rows_wapper">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								二级类别
							</span>
						</div>
						<div class="rows_content fr">
							<div class="selectordef">
								<select id="selectId" class="selectBox secondLevelCatSelect">
								</select>
							</div>
						</div>
					</div>
					
					<div class="rows_wapper">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								新旧程度
							</span>
						</div>
						<div class="rows_content fr">
							<div class="selectordef">
								<select id="condition" class="selectBox">
									<option value="6">六成新</option>
									<option value="7">七成新</option>
									<option value="8">八成新</option>
									<option value="10">全新</option>
								</select>
							</div>
						</div>
					</div>
					
									
					<div class="rows_wapper">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								转让价格
							</span>
						</div>
						<div class="rows_content fr">
							<div class="inputBox">
								<input type="text" class="inputtext" id="currprice"/>
							</div>
						</div>
					</div>
					
						<div class="rows_wapper">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								原价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
						</div>
						<div class="rows_content fr">
							<div class="inputBox">
								<input type="text" class="inputtext" id="formerprice" />
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="block_wapper">
				<div class="block_title">
					<h2>宝贝描述</h2>
				</div>
				<div class="block_content">
					<div class="rows_wapper">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								标题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
						</div>
						<div class="rows_content fr">
							<div class="inputBox">
								<input type="text" class="inputtext" id="itemtitle" placeholder="恰当的标题能增加曝光率哦"/>
							</div>
						</div>
					</div>
	
					
					
					<div id="pictureBox" class="rows_wapper">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								图片上传
							</span>
						</div>
						<div class="rows_content fr">
							<div class="inputBox">
								 <a href="javascript:void(0)" class=" picFileUpload button">上传图片</a>
	               				  <div class="pics"><ul></ul></div>
	               				  <input type="hidden" name="image" id="itemimages"/>
							</div>
						</div>
					</div>
					
					<div class="rows_detail">
						<div class="rows_title fl">
							<span >
								<span class="rows_title_star">*</span>
								详细描述
							</span>
						</div>
						<div class="rows_kingeditor fr">
							<textarea style="width:770px;height:300px;" name="desc"></textarea>
						</div>
					</div>
					
						<div class="rows_wapper">
						<div class="rows_title fl">
							
						</div>
						<div class="rows_content  fr">
							<div class="inputBox">
								<input type="button" class="inputtext publicbutton" value="发布"/>
							</div>
						</div>
					</div>
			</div>
		</div>	
		</form>
	</div>
	<!--E=SubmitForm -->
	
			
	<!-- S=division  -->
	<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="980px" color=#EEEEEE SIZE=3>
	<!-- E=division  -->
	
	<!-- S=copytright -->
	<jsp:include page="/WEB-INF/commons/copyright.jsp"></jsp:include>
	<!-- E=copytright -->
</body>
</html>
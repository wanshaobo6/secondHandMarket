//记录parentId
var parentId = "0";

$(function(){
	//预定事件
	$(".order-now").click(function(){
		if(!isSuccessLogin){
			alert("请先登录");
			window.location="../login.html";
		}
		swal({ 
		  title: "是否需要购买这件宝贝", 
		  text: "<a href='javascript:void(0)' style='color:red'>注:谨慎点击,因为您的信息会被对方知道</a> <p>点击确认您将受到一条短信验证码用于确认</p>", 
		  type: "info", 
		  showCancelButton: true, 
		  closeOnConfirm: false, 
		  showLoaderOnConfirm: true, 
		  html:true,
		},
		function(){ 
		     sendSmsCodeForConfirm();
		});
	
	});
	//图片点击切换大图
	$('#smallimg>ul>li').click(function(){
		var $img=$(this).children()
		var src=$img.attr('src');
		$('#mediumImg').attr('src',src) ;
		
	});
	//点击button按钮提交评论
	$(".addCommentButton").click(function(){
		if(preSubmitComment()){
			var content = $("#commentContent").val();
			var itemId = $("#itemId").val();
			//var parentId = $("#itemId").val();
			$.ajax({
				async:false,
				cache:false,
				url:"../comment/publicMyComment.do",
				data:{itemId:itemId,Content:content,parentId:parentId},
				dataType:"json",
				type:"get",
				success:function(data){
					if(data.status==200){
						 //如去掉无评论提醒
						 if($(".comment_list .reply0").length=='0'){
							 $(".comment_list").html("");
						 }
						//如果是第一层分类
						if(parentId==0){
							var firstLevelComment = ['									 <div id="'+data.data.id,
								'" class="searchCommentId comment reply0">',
								'										 <!-- 头像 目前没有 -->',
								'<!-- 								             <div class="imgdiv">',
								'								                <img class="imgcss"  src="./images/user.jpg"/>',
								'								             </div>',
								' -->								             <div class="conmment_details">',
								'								                 <div style="float:left;">',
								'								                  <span class="comment_name to_name">'+data.data.fromusername,
								' </span>     <span>刚刚</span>',
								'								                </div>',
								'								                <div class="del"> <span  class="reply_he" name="'+data.data.id,
								'">回复</span>',
								'								                  <span  class="show_reply_list">查看回复</span>',
								'								                  <a  > <i class="icon del_comment" name="'+data.data.fromuserid,,
								'" >删除</i></a> ',
								'								                </div>',
								'								                 <div class="comment_content" > '+data.data.content,
								'								                </div>',
								'								            </div>',
								'								           <div class="reply_list"   style="display:inline-block;">   <!--style="display:none;"-->',
								'                                                                               </div> ',
								'								          </div>'].join("");
							$(".comment_list").prepend(firstLevelComment);
						}else{
							//其他子分类
							var classes = $("#"+parentId).attr("class")
							var index = classes.indexOf("reply");
							var layer = classes.substring(index+5,index+6);
							var currlayer = layer+1;
							if(currlayer.substring(0,1)==0){
								currlayer = currlayer.substring(1,2);
							}
							var secondLevelComment = [' <div id="'+data.data.id,
								'" class="reply'+currlayer,
								' child_comment searchCommentId" >',
								'         <span class="reply_name to_name">'+data.data.fromusername,
								'</span>回复<span class="reply_name">'+data.data.tousername,
								'</span>：<span class="reply_content">'+data.data.content,
								' </span> ',
								'         <a  data-id="1" class="del_reply" > ',
								'         	 <span name="'+data.data.id,
								'" class="reply_he" style="margin-left: 2px;">回复</span>',
								'             <i class="icon del_comment" name="'+data.data.fromuserid,
								'" >删除</i>     	 <span style="margin-left: 2px;">刚刚</span>',
								'         </a>   ',
								'</div>'].join("");
							if(layer==0){
								//div类为layer1
								$("#"+parentId+" .reply_list").prepend(secondLevelComment);
							}else{
								//其他类型
								$("#"+parentId+" .del_reply").eq(0).after(secondLevelComment);
							}
						}
						alert("添加成功");
						flushReplyEvent();
						flushDeleteCommentButton();
						unbindDeleteEvent();
						reflushDeleteEvent();
						$("#commentContent").val("");
					}else{
						alert("评论失败");
					}
				}
			});
		}
	});

//为回复添加点击事件
$(".reply_he").click(
	function(){
		var id = $(this).attr("name");
		var toUserStr = $("#"+id+" .to_name").eq(0).text();
		$("#comment_tip").text("回复"+toUserStr+" :");
		parentId = id;
		//将输入框正中央显示
		var top = $("#commentContent").offset().top;
		$(document).scrollTop(top-400); 
	}
);
	
  //点击要回复的人将parentId切换0
$("#comment_tip").click(
		function(){
			$("#comment_tip").text("");
			parentId = "0";
		}
	);
//查看回复和收起回复
$(".show_reply_list").click(
	function(){
		//获得当前收起回复所属于的div
		var divId = $(this).prev().attr("name");
		if($("#"+divId+" .reply_list").css("display")=="none"){
			$("#"+divId+" .reply_list").css("display","inline-block")
			$(this).text("收起回复");
		}else{
			$("#"+divId+" .reply_list").css("display","none")
			$(this).text("查看回复");
		}
	}
);
//自己发表的评价显示删除按钮
setTimeout(function () {
	flushDeleteCommentButton();
	}, 400);

//点击删除按钮删除自己发布的评论
reflushDeleteEvent();
//点击图片收藏闲置
$(".doCollectionImg").click(
	function(){
		//用户登录验证
		if(!isSuccessLogin){
			//异常情况  转跳登录界面
			alert("请先登录");
			window.location="../login.html";
		}
		var itemId = $("#itemId").val();
		if(itemId==""){
			//异常情况 
			alert("系统异常");
			return;
		}
		 $.ajax({
			 async:false,
			 cache:false,
			 url:"../collection/changeMyCollection.do",
			 data:{itemId:itemId},
			 dataType:"json",
			 type:"get",
			 success:function(data){
				 if(data.status==200){
					 if(data.data=="0"){
						 $(".doCollectionImg").attr("src","../image/starno.png");
						 $(".collectionTip").text("喜欢我就点收藏吧--->");
					 }else{
						 $(".doCollectionImg").attr("src","../image/staron.png");
						 $(".collectionTip").text("您已收藏了这件宝贝,心动不如心动^_^");
					 }
			 	}else{
			 		alert(data.msg);
			 	}
			 }
		 });
	});
//---------------------------------------------------------------------------------------------------
})
//评论前验证
function preSubmitComment(){
	//用户登录验证
	if(!isSuccessLogin){
		alert("请先登录");
		window.location="../login.html";
	}
	//内容非空验证
	var content = $("#commentContent").val();
	if(content.trim()==""){
		alert("请输入留言内容");
		return false;
	}
	return true;

}
//刷新回复事件
function flushReplyEvent(){
	//为回复添加点击事件
	$(".reply_he").click(
		function(){
			var id = $(this).attr("name");
			var toUserStr = $("#"+id+" .to_name").eq(0).text();
			$("#comment_tip").text("回复"+toUserStr+" :");
			parentId = id;
			//将输入框正中央显示
			var top = $("#commentContent").offset().top;
			$(document).scrollTop(top-400); 
		}
	);
}
//根据用户权限刷新删除按钮
function flushDeleteCommentButton(){
	$(".del_comment").each(
			function(){
				var fromuserid = $(this).attr("name");
				if(fromuserid==($("#currentUserId").val())){
					$(this).css("display","inline-block");
				}
			}
		);
}
//解除删除评论事件
function unbindDeleteEvent(){
	$(".del_comment").unbind();
}
//刷新点击删除按钮删除自己发布的评论使事件
function reflushDeleteEvent(){
	$(".del_comment").click(
			 function(){
				 var commentId = $(this).parents(".searchCommentId").eq(0).attr("id");
				 if(confirm("确定删除该评论吗")){
					 $.ajax({
						 async:false,
						 cache:false,
						 url:"../comment/deleteMyComment.do",
						 data:{commentId:commentId},
						 dataType:"json",
						 type:"get",
						 success:function(data){
							 if(data.status==200){
								 //删除成功
								 $("#"+commentId).remove();
								 //如果没有评论了处理页面处理
								 if($(".comment_list .reply0").length=='0'){
									 var noComments=['<div class="no_comment">',
										 '<img  alt="无评论" src="../image/leaveMessage.png">',
										 '</div>'].join(""); 
									 $(".comment_list").html(noComments);
								 }
								 alert("删除成功");
							 }else{
								 alert("权限不足");
							 }
						 }
					 });
				 }
			 }
		);
}
//发送验证码
function sendSmsCodeForConfirm(){
	 var itemId = $("#itemId").val();
	 if(itemId == "")
		 return;
	 $.ajax({
		 async:true,
		 cache:false,
		 url:"../trade/sendTradeConfirmCode.do",
		 data:{itemId:itemId},
		 dataType:"json",
		 type:"get",
		 success:function(data){
			 if(data.status==200){
				 //发送成功
			var itemId = $("#itemId").val();
				 swal({ 
					  title: "发送成功", 
					  text: "请输入验证码：",
					  type: "input", 
					  showCancelButton: true, 
					  closeOnConfirm: false, 
					  animation: "slide-from-top", 
					  inputPlaceholder: "输入验证码" 
					},
					function(inputValue){ 
					  if (inputValue === false) return false; 
					  
					  if (inputValue === "") { 
					    swal.showInputError("你需要输入一些话！");
					    return false 
					  } 
					  var result = addNewTrade(itemId,inputValue);
					  if(result.status== '200'){
					    swal("成功购买"); 
					    return true;
					  }else if(result.status== '501'){
					    swal.showInputError("验证码错误请重新输入！");
					    return false;
					  }else if(result.status== '500'){
					    swal.showInputError(data.msg);
					    return false;
					  }else{
						  swal.showInputError("未知错误");
						  return false;
					  }
					});
			 }else{
				 //发送失败
				swal.showInputError(data.msg);
				 return true;
			 }
		 }
	 });
}

//新增交易
function addNewTrade(itemId,smsCode){
	var result ;
	 $.ajax({
		 async:false,
		 cache:false,
		 url:"../trade/makeADeal.do",
		 data:{itemId:itemId,smsCode:smsCode},
		 dataType:"json",
		 type:"get",
		 success:function(data){
				 result= data;
		 }
	 });
	 return result;
}

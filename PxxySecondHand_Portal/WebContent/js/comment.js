$(function(){
	 //返回上一页
	 $("#goBackButton").click(function(){
		 window.history.go(-1);
	 });
})

function enableChooseStar(){
	$("#starLevel").mousemove(function(e) {  
	     var positionX=e.pageX-$(this).offset().left; //获取当前鼠标相对img的X坐标 v
	     var starLevel = Math.round(positionX/10)==10?Math.round(positionX/10):Math.round(positionX/10)+1;
	     console.log(starLevel);
	     $(this).removeClass().addClass("start"+starLevel);
	     $(".currStar").text(starLevel/2+"星");
	 })  
}
	 
 function disableChooseStar(){
	$("#starLevel").unbind("mousemove");
 }
 
function validateComment(){
	var content = $(".commentContentTextarea").val();
	if(content.trim()==""){
	   alert("请输入评论内容");
		return false;
	}
	return true;
}
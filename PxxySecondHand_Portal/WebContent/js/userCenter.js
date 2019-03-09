$(function(){
	//导航栏添加按钮触发事件
	$(".navigations>li").click(
		function(){
			if($(this).attr("class")=="navselected")
				return;
			$(".navselected").removeClass("navselected").addClass("navunselected");
			$(this).removeClass("navunselected").addClass("navselected");
			var id = $(this).children('a').attr("id");
			document.getElementById(id).click();
		}
	);
});
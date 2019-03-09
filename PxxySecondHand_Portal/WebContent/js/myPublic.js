$(function(){
	//点击标题进入详情页面
	$(".itemTitle").click(
			function(){
			  window.parent.location = "/PxxySecondHand_Portal/item/showItem.do?itemId=" + $(this).attr("name");
			}		
		);
	//刷新闲置删除按钮
	flushDeleteItemBtn();
	//擦亮宝贝
	$(".polishItem").click(function(){
		var itemId = $(this).attr("name");
		if(itemId == null)
			return ;
		$.ajax({
			async:true,
			cache:false,
			url:"polishItem.do",
			data:{itemId:itemId},
			dataType:"json",
			type:"get",
			success:function(data){
				if(data.status=="200"){
					//改变改按钮class状态
					if(data.data == 0)
						$("#polishItem"+itemId).removeClass("polishItem").css("backgroundColor","#c0c0c0");
						$("#polishItem"+itemId).text("已擦亮");
						$("#polishItem"+itemId).attr("disabled","true");
				}else if(data.status=="500"){
					swal("ERROR！", data.msg,"error");
				}
			}
		})
	});
	//闲置下架
	$(".changeupanddown").click(function(){
		var itemId = $(this).attr("name");
		if(itemId == null)
			return ;
		$.ajax({
			async:true,
			cache:false,
			url:"changeupanddown.do",
			data:{itemId:itemId},
			dataType:"json",
			type:"get",
			success:function(data){
				if(data.status=="200"){
					//改变改按钮class状态
					if(data.data == 0){
						//已下架
						$("#changeIsInTrade"+itemId).text("闲置上架");
						$("#changeIsInTrade"+itemId).css("backgroundColor","red");
					}else if(data.data == 1){
						//已上架
						$("#changeIsInTrade"+itemId).text("闲置下架");
						$("#changeIsInTrade"+itemId).css("backgroundColor","#2e94d1");
					}
				}else if(data.status=="500"){
					swal("ERROR！", data.msg,"error");
				}
			}
		})
	});
})
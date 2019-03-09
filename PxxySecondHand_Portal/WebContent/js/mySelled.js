$(function(){
	//点击商品标题进入详情页面
	$(".itemTitle").click(
		function(){
		  window.parent.location = "/PxxySecondHand_Portal/item/showItem.do?itemId=" + $(this).attr("name");
		}		
	);
	
	//查看评价
	$(".seeComment").click(function(){
		 let tradeId = $(this).attr("name");
		 window.location ="/PxxySecondHand_Portal/trade/showCommentByTradeId.do?tradeId="+tradeId;
	});
	//刷新所有删除按钮
	flushDeleteButton();
	
	//刷新所有的确认送达按键
	confirmArrive();
	//取消交易
	$(".sellerCancelTradeBtn").click(function(){
		let tradeId = $(this).attr("name");
		if(tradeId == null)
			return;
		Swal({
			  title: '确定取消改交易吗?',
			  text: "你将无法恢复该交易!",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '确定取消!'
			}).then((result) => {
			  if (result.value) {
				$.ajax({
					async:true,
					cache:false,
					url:"cancelTheTrade.do",
					data:{tradeId:tradeId},
					dataType:"json",
					type:"get",
					success:function(data){
						if(data.status=="200"){
						 Swal(
							      'Canceled!',
							      '该交易已被取消.',
							      'success'
							    ).then(() => {
							    	  window.location = "/PxxySecondHand_Portal/trade/getAllOfMySelled.html";
							    })
						}else if(data.status=="500"){
							swal("ERROR！", data.msg,"error");
						}
					}
				})
			   
			  }
			})
	});
	//确定送达
	$(".sellerConfirmTradeBtn").click(function(){
		let tradeId = $(this).attr("name");
		if(tradeId == null)
			return;
		Swal({
			  title: '确定送达?',
			  text: "你将无法恢复该交易!",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '确定取消!'
			}).then((result) => {
			  if (result.value) {
				$.ajax({
					async:true,
					cache:false,
					url:"confirmMyTrade.do",
					data:{tradeId:tradeId},
					dataType:"json",
					type:"get",
					success:function(data){
						if(data.status=="200"){
						 Swal(
							      'Canceled!',
							      '该交易已送达.',
							      'success'
							    ).then(() => {
							    	  window.location = "/PxxySecondHand_Portal/trade/getAllOfMySelled.html";
							    })
						}else if(data.status=="500"){
							swal("ERROR！", data.msg,"error");
						}
					}
				})
			   
			  }
			})
	})
	
	
})

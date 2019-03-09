$(function(){
	//发送消息给站长
	$(".advicebutton").click(function(){
		//必须登录
		if(!isSuccessLogin){
			Swal({
				  title: '请先登录!',
				  imageUrl: 'image/timg.jpg',
				  imageWidth:250,
				  imageHeight: 200,
				  imageAlt: 'Custom image',
				  animation: true
				})
			return;
		}
		//必须有输入
	 let content = $("#adviceContent").val();
	 if(content==null || content.trim() == ""){
		 Swal({
			  title: '请输入内容!',
			  imageUrl: 'image/timg.jpg',
			  imageWidth: 250,
			  imageHeight: 200,
			  imageAlt: 'Custom image',
			  animation: true
			})
		return;
	 }
	 //发送给站长
	 $.ajax({
			async:false,
			cache:false,
			url:"mail/sendMailToWebMaster.do",
			data:{msg:content},
			dataType:"json",
			type:"get",
			success:function(data){
				if(data.status == 200){
					Swal({
						  type: 'success',
						  title: '成功',
						  text: "发送成功,我们将尽快给您答复",
						})
						$("#adviceContent").val("");
				}else if(data.status == 500){
					Swal({
						  type: 'error',
						  title: '错误',
						  text: data.msg,
						})
				}
			}
		});
	    
	})
})

function sendToWebMaster(){
	
}
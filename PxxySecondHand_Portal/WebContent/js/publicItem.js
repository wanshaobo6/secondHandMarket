//富文本编辑器对象
	var itemAddEditor;
	//上次发布商品名
	var formerItemName;
	$(function(){
		itemAddEditor = MyEditor.createEditor("#itemAddForm [name=desc]");
		//初始化类目选择和图片上传器
		MyEditor.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			MyEditor.changeItemParam(node, "itemAddForm");
		}});
		//初始化一级目录
		var parentId = initFirstLevelCatSelect();
		//初始化二级目录
		selectItemCatByParentId(parentId);
		//为每个一级目录添加事件
		$(".firstLevelCatSelect").children().click(
			function(){
				var parentId = this.value;
				$(".secondLevelCatSelect").html("");
				selectItemCatByParentId(parentId);
			}		
		);
		//为发布按钮添加浮动事件
		$(".publicbutton").hover(
		   function(){
			   $(this).css("backgroundColor","#FF9933");
		   },
		   function(){
			   $(this).css("backgroundColor","#FFCC66");
		   }
		);
		//为发布按钮添加点击事件
		$(".publicbutton").click(
			   function(){
				  if(checkFormDataVailable())
				    addItem();
			   }
			);
	})
	function initFirstLevelCatSelect(){
		var firstCatId ;
		$.ajax({
			async:false,
			cache:true,
			url:"itemcat/selectItemCatListByParentId.do",
			dataType:"json",
			type:"post",
			success:function(data){
				for(var i in data){
					if(i==0)
					  firstCatId = data[i].id;
					$(".firstLevelCatSelect").append("<option value='"+data[i].id+"'>"+data[i].categoryname+"</option>");
				}
			}
		});
		return firstCatId;
	}
	function selectItemCatByParentId(parentId){
		$.ajax({
			async:false,
			cache:true,
			url:"itemcat/selectItemCatListByParentId.do",
			data:{parentId:parentId},
			dataType:"json",
			type:"post",
			success:function(data){
				for(var i in data){
					$(".secondLevelCatSelect").append("<option value='"+data[i].id+"'>"+data[i].categoryname+"</option>");
				}
			}
		});
	}
	
	//检验表单的有效性
	function checkFormDataVailable(){
		var reg = new RegExp("^[0-9]+([.]{1}[0-9]+){0,1}$");
		
		//表单所有内容
		var itemtitle = $("#itemtitle").val();
		if(formerItemName == itemtitle){
			sweetAlert("Oops...", "请勿重复发布!", "error");
			return false;
		}
		var currprice = $("#currprice").val();
		if(!reg.test(currprice)){
			sweetAlert("Oops...", "转卖价格格式错误!", "error");
			return false;
		}
		var formerprice = $("#formerprice").val();
		if(!reg.test(formerprice)){
			sweetAlert("Oops...", "原价格式错误!", "error");
			return false;
		}
		var itemimages = $("#itemimages").val();
		if(itemimages=null){
			sweetAlert("Oops...", "至少需要插入一张图片!", "error");
			return false;
		}
		return true;
	}
	
	function  addItem(){
		//表单所有内容
		var itemtitle = $("#itemtitle").val();
		var currprice = $("#currprice").val();
		var formerprice = $("#formerprice").val();
		var itemimages = $("#itemimages").val();
		var token = $.cookie('Login_Token');
		var categoryid = $('#selectId>option:selected').val();
		var icondition = $('#condition').val();
		var itemdesc = $(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html();
		$.ajax({
			async:false,
			cache:true,
			url:"item/addItem.do",
			data:{itemtitle:itemtitle,itemdesc:itemdesc,currprice:currprice,formerprice:formerprice,
				itemimages:itemimages,token:token,categoryid:categoryid,icondition:icondition},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.status==200){
					formerItemName = itemtitle;
					swal({
						  title: "Success!",
						  text: "闲置发布成功!",
						  type: "success",
						  confirmButtonText: "Cool"
						});
				}else{
					sweetAlert("oh...", "发布异常!请稍后再试", "error");
				}
			}
		})
	}
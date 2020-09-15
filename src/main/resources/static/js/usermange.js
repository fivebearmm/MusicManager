
$(function($) {
	 
   var $table = $("#usertab");
		
	$table.bootstrapTable({
	
		height:'600px',
		
		//设置请求的URL，向后台拉取数据
		
		url:"/UserManager/getUserListController.do",
		
		//设置为http GET请求
		
	   method:"GET",
	   
	   contentType: 'text/json,charset=utf-8',
	   
	   dataType: 'json',
	   
		columns:[
			
			{
                field:'select',
                
			    checkbox:true,
			    
			    align:'center',
			    
			    valign:'middle',
			},
			{
				field:'id',
				
				title:'编号',
				
				align:'center',
				
				valign:'middle',
				
			},
			{
				field:'username',
				
				title:'用户名',
				
				align:'center',
				
				valign:'middle',
				
			},
			{
				field:'sex',
				
				title:'性别',
				
				align:'center',
				
				valign:'middle',
				
				//格式化后台数据，0为男
				
				formatter:function(value,row,index){
					
					return ((typeof(value) != "undefined")&&(value == 0))?"男":"女";
					
				 }
				
			},
			{
				field:'age',
				
				title:'年龄',
				
		        align:'center',
		        
		      valign:'middle',
		      
         	},
		   {
		     field:'mobile[phone',
		     
		     title:'电话',
		     
		     align:'center',
		     
		    valign:'middle',
		    
        	},
		  {
		     field:'address',
		     
		     title:'住址',
		     
		     align:'center',
		     
		    valign:'middle',
		    
     	}
        	
	],
	
	onCheck:function(row){
		$("#remove").removeAttr("disabled");
	},
	onUncheckAll:function(){
		$("#remove").attr("disabled","disabled");
	},
	onCheckAll:function(){
		$("#remove").removeAttr("disabled");
	},
	onUncheckAll:function(row){
		var selects = $table.bootstrapTable('getSelecttions');
		
		if(selects.length == 0){
			
			$("#remove").attr("disabled","disabled");
		}
		
	   }
	
	});

	//添加用户功能
	
	function addUser(){
			
		var userName = $("#username").val();
		
		var password = $("#password").val();
		
		//var sex = $("#sex").val();
		
		var age = $("#age").val();
		
		var mobilePhone = $("#mobilephone").val();
		
		var address = $("#address").val();
		
		//构建请求参数
		
		var param = {
				
				userName:userName,
				
				password:password,
				
				//sex:sex,
				
				age:age,
				
				mobilePhone:mobilePhone,
				
				address:address
		};
		
		$.ajax({
			
			url:"/UserManager//addUserController.do",
			 
			data:param,
			
			contentType: 'text/json,charset=utf-8',
			
			dataType: 'json',
			
			success:function(data){
				
				//添加成功刷新页面
				
				if((typeof(data) != "undefined") && (data == 0)){
					
					$table.bootstrapTable('refresh');
					
				}
 			
			},complete:function(){
				
				//页面提交时隐藏表单页
				
			    $('#myModal').modal('hide');
			
			},
		   
			 context:this
			 
		});
		
	}
	//为添加按钮绑定事件
		   
		  $("#add_user_button").bind("click",addUser);
		  
		  function delUser(){
				
			  var selects = $table.bootstrapTable("getSelections");
				
				if(selects.length == 0){
					
					return;
					
				}
				
				var userIds = "";
				
				for(var i = 0;i < selects.length;i++){
					
					userIds = userIds + selects[i].id + ",";
				}
				
				var param = {
						
						userIds:userIds
				};
				
				$.ajax({
					
					url:"/UserManager//deleteUsersController.do",
					 
					data:param,
					
					success:function(data){
						
						//添加成功刷新页面
						
						if((typeof(data) != "undefined") && (data == 0)){
							
							$table.bootstrapTable('refresh');
							
						}
						
						$("#remove").attr("disabled","disabled");
		 			
					},
					 context:this
					 
				});
				
			}
		  
		  $("#remove").bind("click",delUser);
		  
});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
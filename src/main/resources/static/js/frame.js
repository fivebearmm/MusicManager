/*(function($){
	
	function login(event){
		
		event.preventDefault();
		
		var userName = $("#username").val();//获取用户名
		
		var password = $("#password").val();//获取密码
		
		$.post("/UserManager/loginController.do",{
			  username:userName,
			  password:password
		},
		       
		       function(data){
		    	   
		    	   if((typeof(data) != "undefined")&&
		    			   (null != data)&&(0 == data)){
		    		   
		    		   //登录成功隐藏登录表单
		    		   $(".contain").hide();
		    		   
		    		   //登录成功跳转到用户列表页
		    		   $("#content").load(
		    				   
		    				   "/UserManager/listUserController.do");
		    	   }
		    	   
 		       });
		       
	     }
	
	$("#loginbtn").bind("click",login);
	
})($);*/
     


	
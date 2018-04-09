<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-1.8.3.js"></script>
</head>
<body>

	<script type="text/javascript">
		var students = new Array();  
		students.push({name: "李四",pwd: "123"});   
		students.push({name: "张三",pwd: "332"});   
		var student = {};  
		student.name = "李刚";  
		student.pwd = "888";  
		student.students = students;  
		console.log(student);
		
		$.ajax({  
   	 			type: "POST",  
    			url: "http://127.0.0.1:9086/pagedemo/s",  
    			data: JSON.stringify(student),//将对象序列化成JSON字符串  
    			dataType:"json",  
    			contentType : 'application/json;charset=utf-8', //设置请求头信息  
    			success: function(data){  
    				console.log("success");
   				}
			}); 
</script>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="js/jquery-1.8.3.js"></script>

</head>
<script type="text/javascript">

	/* jQuery(function() { 
		$.post(
				"http://192.168.10.2:8080/dzxz-youzan-webapp/bdp/product/list", 
				{
					ids:"816f1492900642b0bcdbb4612b8e3207,671799b0d1214da9a365d365e302d24d"
				}, 
				function(data){
					console.info(data);
					}
				);
	}) */ 
	
	/* jQuery(function() { 
		$.post(
				"http://192.168.20.3:8080/dzxz-youzan-webapp/bds/home/enable", 
				{
					id:"bdce73711997418394425ac3a11f6e6f"
				}, 
				function(data){
					console.info(data);
					}
				);
	}) */
	var object = {};
	object.businessname = "测试商家";
	object.businessaddress = "测试商家地址";
	object.businessltype = "1";
	object.isneedreservation = "0";
	object.productid = "0e73e9bef05f43959db3cfccd42f6904";
	object.productname = "ss3";
	object.productimg = "https://img.yzcdn.cn/upload_files/2017/06/19/FmjxzQEDd_ghL2WG-9v9H4DCploe.png?imageView2/2/w/100/h/100/q/75/format/webp";
	object.parentcompanyid = "15";
	object.parentcompanyname = "555";
	var parentcompanyname = {};
	parentcompanyname.operatorname = "666";
	parentcompanyname.operatorid = "666";
	parentcompanyname.operatorcompanyid = "666";
	parentcompanyname.operatorcontent = "666";
	object.operationInfo = parentcompanyname;
	
	jQuery(function() { 
		$.post(
				"http://192.168.20.46:11750/api/hotel/addhotelproduct", 
				{
					accesstoken:"111111",
					newaccesstoken:"192.168.20.3",
					timestamp:"2018-01-16 14:20:20",
					data : JSON.stringify(object)
				}, 
				function(data){
					console.info(data);
					}
				);
	})
			
</script>

<body>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="js/jquery-1.8.3.js"></script>

<script src="js/core.js"></script>
<!-- <script src="js/cipher-core.js"></script> -->
<script src="js/crypto-js.js"></script>
<script src="js/aes.js"></script>
<script src="js/mode-ecb.js"></script>


</head>
<script type="text/javascript">
	//key解密
	var key = CryptoJS.enc.Base64.parse("v4dxhiengzUDxCYV+ga1Fw==");
	
	//登录字符串
	var stu= '{loginname:\'jianglan\',password:\'123456\'}';
	var srcs = CryptoJS.enc.Utf8.parse(stu);
	
	//登录字符串加密结果
	var encrypted = CryptoJS.AES.encrypt(srcs, key, {
		mode : CryptoJS.mode.ECB,
		padding : CryptoJS.pad.Pkcs7
	});
	
	console.log(encrypted);
	
	/* alert("Encrypt："+encrypted.toString());
	
	alert("base64加密后"+CryptoJS.enc.Base64.stringify(encrypted.ciphertext)); */
	
	
	//解密
	var decrypt = CryptoJS.AES.decrypt(
			encrypted.toString(),
			key, {
				mode : CryptoJS.mode.ECB,
				padding : CryptoJS.pad.Pkcs7
			});
	
	console.log(decrypt);
	/* alert("Decrypt: ");
	alert(CryptoJS.enc.Utf8.stringify(decrypt).toString()); */
	
	
	

	function btn(){
		
		var stu= '{loginname:\'jianglan\',password:\'123456\'}';
		
		var encrypted = CryptoJS.AES.encrypt(CryptoJS.enc.Utf8.parse(stu), key, {
			mode : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7
		});
		
		alert(encrypted);
		
		$.post('http://192.168.20.25:8080/activity-platform-web/login', {
			'data': encrypted.toString(),
			'seckey': CryptoJS.enc.Base64.stringify(key)
		}, function(data) {
			console.log(data);
		});
		
	} 

</script>

<body>

	<button value="button" style="width: 100px; height: 50px"
		onclick="btn()"></button>

</body>
</html>
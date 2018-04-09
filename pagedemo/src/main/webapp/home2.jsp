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
<script src="js/crypto-js.js"></script>
<script src="js/aes.js"></script>
<script src="js/mode-ecb.js"></script>

</head>
<script type="text/javascript">
	//key解密
	var key = CryptoJS.enc.Base64.parse("A5RHRbfO3r/CoOkfwwxY5A==");

	//登录字符串
	var user = '{loginname:\'jianglan\',password:\'123456\'}';

	var encrypted = encrypt(user);
	console.log(encrypted);
	alert("加密后的数据： " + encrypted);

	var decrypted = decrypt('/IHpt0boNCEM1oQD1iBoeOSJBDaQgfCUtm7zsJK7gAKT6cKhzdt3hBkKPaCupMPnlGFuvXXdTHGShhvrOf2igA4+07gXUET6z8tkh5HEilULcdb/jFa19pTy9KfmmcmEr2pRMn/O6++LBaSigGr1RiwKLmVepcI+JCXxFcSOEZfI4KSY6NZvv/n0AMLH/m3jQ/tO/sBB1OH2HRGq8eMKzn7C4cqP7sToDI7JcRW/gdqEAsa5wH8Fy7p5MN3tqcYCAGjMn32q5hNlYupDRdAT/yTfYAVSs+XnXNVFC3+nVs6efeRwzVrXOsUkY8ectwW6ZU0hZ/ll5us=');
	console.log(decrypted);
	alert("解密后的数据： " + decrypted);

	//aes加密函数
	//data  原始数据
	function encrypt(data) {
		//对数据进行utf8格式化
		var srcs = CryptoJS.enc.Utf8.parse(data);
		//对数据进行加密
		var encrypted = CryptoJS.AES.encrypt(srcs, key, {
			mode : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7
		});
		return encrypted.toString();
	}

	//aes解密函数
	//data 加密后的数据
	function decrypt(data) {

		var decrypt = CryptoJS.AES.decrypt(data, key, {
			mode : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7
		});
		return CryptoJS.enc.Utf8.stringify(decrypt).toString();
	}

	//访问函数
	function btn() {

		var user = '{loginname:\'jianglan\',password:\'123456\'}';

		var encrypted = encrypt(user);

		$.post('http://192.168.10.16:8080/activity-platform-web/login', {
			'data' : encrypted,
			'seckey' : CryptoJS.enc.Base64.stringify(key)
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
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

	jQuery(function() { 
		/* var ids = ["f619942cc05e4faf974ade46916bb785","99dae1d3903e4090a74d09b223d07ccb"];
		$.post(
				"http://192.168.10.2:8080/dzxz-youzan-webapp/bds/shop/batchEnable",
				{
					'ids' : ids
				}, 
				function(data){
					console.info(data);
					}
				); */
		
		//  测试产品addnew
		/* var merchandise = {};
		merchandise.name = "测试6666";
		merchandise.presale = "true";
		merchandise.protype = "1";
		merchandise.groupId = "98aa5ba4ae2a419e999f051bb127c133";
		merchandise.categoryId = "0e8d996a4fc243b580c0708f6b8b17b1";
		merchandise.displayStock = "false";
		merchandise.distribution = "true";
		var resEntries = new Array();
		resEntries.push({resType : "11", resPath : "1111", delFlg : "false"});
		resEntries.push({resType : "22", resPath : "2222", delFlg : "false"});
		var invEntries = new Array();
		invEntries.push({number : "1414124", specification : "红-150-湖北", salePrice : "20", costPrice : "38", distPrice : "15", stockQty : "3500", saledQty : "3000", delFlg : "false"});
		invEntries.push({number : "4654165", specification : "黑-180-四川", salePrice : "30", costPrice : "48", distPrice : "25", stockQty : "3500", saledQty : "3000", delFlg : "false"});
		invEntries.push({number : "8451613", specification : "金-200-北京", salePrice : "40", costPrice : "58", distPrice : "35", stockQty : "3000", saledQty : "2000", delFlg : "false"});
		var orgEntries = new Array();
		orgEntries.push({shopId : "f6cba527216144d686347ca8bb48117d", useType : "1", delFlg : "false"});
		orgEntries.push({shopId : "f6cba527216144d686347ca8bb48117d", useType : "0", delFlg : "false"});
		merchandise.resEntries = resEntries;
		merchandise.invEntries = invEntries;
		merchandise.orgEntries = orgEntries; 
		var url = "http://192.168.20.3:8080/dzxz-youzan-webapp/bdp/merchant/addnew";
		*/
		
		//  测试店铺list
		/* var merchandise = {};
		var ids = ["f6cba527216144d686347ca8bb48117d","7b6a7e71b58a41efa22c51de3a460153"];
		merchandise.ids=ids;
		//merchandise.id = "f6cba527216144d686347ca8bb48117d";
		//merchandise.name = "555";
		//merchandise.linker = "测试人14";
		//merchandise.linkTel = "13888888888";
		//merchandise.merchantId = "";
		//merchandise.organizationId = "";
		var url = "http://192.168.20.3:8080/dzxz-youzan-webapp/bds/shop/list"; */
		
		//  测试店铺addnew\update
		/* var merchandise = {};
		merchandise.id = "2762218164934547b53704208f85af00";
		merchandise.name = "DDD";
		merchandise.wxMsg = "ss";
		merchandise.number = "165461641";
		merchandise.linker = "sss";
		merchandise.linkTel = "13888888888";
		merchandise.imagePath = "http://www.baidu.com/img/bd_logo1.png";
		merchandise.publicNumber = "135161";
		merchandise.offlineStore = "true";
		merchandise.productQty = "1321";
		merchandise.visitorQty = "131";
		merchandise.browseTimes = "11";
		merchandise.saledAmount = "121.00";
		merchandise.microPageQty = "11";
		merchandise.paidProductQty = "444";
		merchandise.securedTransactions = "true";
		merchandise.personalCertification = "true";
		merchandise.description = "46416541";
		//var url = "http://192.168.10.2:8080/dzxz-youzan-webapp/bds/shop/addnew";
		var url = "http://192.168.10.2:8080/dzxz-youzan-webapp/bds/shop/update"; */
		
		//测试店铺list
		var merchandise = {};
		merchandise.synchro = "0";
		//var ids = ["01e955cb77b8480ea1eec532c31baa36","13aa4fc7f3d945e6bbb0582f4245fa62"];
		//merchandise.ids=ids;
		//merchandise.id = "f6cba527216144d686347ca8bb48117d";
		//merchandise.name = "555";
		//merchandise.linker = "测试人14";
		//merchandise.linkTel = "13888888888";
		//merchandise.merchantId = "";
		//merchandise.organizationId = "";
		//var data = JSON.stringify(merchandise);
		//console.log(data);
		var url = "http://192.168.20.3:8081/dzxz-youzan-webapp/bdp/product/list";
		var ids = ["01e955cb77b8480ea1eec532c31baa36","13aa4fc7f3d945e6bbb0582f4245fa62"];
		//var ids = new Array();
		//ids.push("01e955cb77b8480ea1eec532c31baa36");
		//ids.push("13aa4fc7f3d945e6bbb0582f4245fa62");
		$.ajax({  
	 			type: "POST",  
				url: url,  
				data: merchandise,//将对象序列化成JSON字符串  
			//	dataType:"json",  
				contentType : 'application/x-www-form-urlencoded;charset=utf-8', //设置请求头信息  
				success: function(data){  
					console.log(data);
				}
		});
		
		/* var shop = {};
		var ids = ["1", "4"];
		shop.ids=ids;
		var url = "http://192.168.20.3:8081/dzxz-youzan-webapp/bds/shop/list";
		
		var data = JSON.stringify(shop);
		console.log(data);
		$.ajax({  
	 			type: "POST",  
				url: url,  
				data: data,//将对象序列化成JSON字符串  
				dataType:"json",  
				contentType : 'application/json;charset=utf-8', //设置请求头信息  
				success: function(data){  
					console.log(data);
				}
		}); */
	}) 

</script>

<body>

</body>
</html>
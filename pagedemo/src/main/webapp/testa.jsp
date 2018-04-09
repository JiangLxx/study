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
		function testa(){
			$.post(
					"http://127.0.0.1:9086/pagedemo/a",
					{
						'name' : 'YYYY',
						'params' : JSON.stringify(params)
					}, 
					function(data){
						console.info(data);
						}
					);
		}
		
	})  */

</script>

<body>
	<form id="form" name="form"  method="post" action="http://127.0.0.1:9086/pagedemo/a">
  <table>
    <tr>
      <td><input type="text" name="b[0].name" value="11"/></td>
      <td><input type="text" name="b[0].sex" value="11"/></td>
    </tr>
    <tr>
      <td><input type="text" name="b[1].name" value="11"/></td>
      <td><input type="text" name="b[1].sex" value="11"/></td>
    </tr>
    <tr>
      <td><input type="text" name="b[2].name" value="11"/></td>
      <td><input type="text" name="b[2].sex" value="11"/></td>
    </tr>
    <tr>
    	<td><input type="submit" value="提交" /></td>
    </tr>
  </table>
</form>
</body>
</html>
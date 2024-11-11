<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	*{
		margin: 0;
		padding : 0;
	}
	div{
		display: flex;
		justify-content: center;
		align-items: center;
		height: 100vh;
		width: 100%; 
		flex-direction : column;
		background-color : #1ABC9C;
	}
	
	div li{
		list-style : none;
	}
	div input{
		border : 0px;
		margin : 30px;
		padding : 10px 30px 10px 30px;
		border-radius: 8px;
	}
	
	#data{
		background-color : #4992d3;
	}

	form{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		background-color: #2c3e50;
	}
</style>
</head>

<body>
<%
	if(session.getAttribute("name")==null){
		response.sendRedirect("login.jsp");
	}
%>
<input type ="hidden" id="data" value = "<%= request.getAttribute("data")%>">
	<div>
		<form action = "profile" method = "post" enctype="multipart/form-data">
			<li><input type = "file" name = "image" id= "data"></li>
			<li><input type = "text" placeholder = "Enter name here" name = "name"></li>
			<li><input type = "text" placeholder = "Enter about here" name = "about"></li>
			<li><input type = "submit"></li>
		</form>
	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type = "text/javascript">
	var data = document.getElementById("data").value;
	if(data=="updated"){
		swal("congratulation","account created sucessfully","sucess");
	}
</script>
</body>
</html>
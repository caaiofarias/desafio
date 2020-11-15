<!DOCTYPE html>
	<%@ page isELIgnored="false" %>
	<%@ page contentType="text/html;charset=UTF-8"%>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/sidebar.css"/>
	</head>
	<body>
			<% String name = request.getParameter("username"); %>
		<div style="position: absolute;right: 50px">
		<h2>Bem vindo, <strong><%=name%></strong></h2>
		</div>
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
 			<a href="${pageContext.request.contextPath}/create.jsp"><div style="color:saffron">Criar Usuário</div></a></br></br>
 			<a href="${pageContext.request.contextPath}/allUsers.jsp"><div style="color:saffron">Listar Usuário</div></a></br></br>
 			<a href="${pageContext.request.contextPath}/update.jsp"><div style="color:saffron">Editar Usuário</div></a></br></br>
			<a href="${pageContext.request.contextPath}/delete.jsp"><div style="color:saffron">Deletar Usuário</div></a></br></br>
		</div>
		<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
	<script>
	function openNav() {
		document.getElementById("mySidenav").style.width = "250px";
		}
	function closeNav() {
		document.getElementById("mySidenav").style.width = "0";
		}
	</script>
	</body>
</html> 
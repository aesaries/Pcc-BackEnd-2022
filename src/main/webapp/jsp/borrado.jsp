<!Doctype html>
<html>
	<head>

		
		<jsp:include page="styles.jsp"/>
	</head>

	<body>
	<jsp:include page="navbar.jsp"/>
	<div class="container">
	<%! 
		String idBorrado = request.getParameter("titulo");
		
	 %>

		<br>
		<br>
		<br>
		<br>
		<h4> <%=idBorrado%> </h4>
		<br>
		

	</div>
		<jsp:include page = "script.jsp"/>

	</body>
</html>
<!Doctype html>
<!-- directiva para importar clases-->
<%@page import="domain.Producto"%>
<%@page import="java.util.List"%>

<html lang="es">
	<head>
		<!-- boostrap -->
		<jsp:include page="styles.jsp"/>

	</head>

	<!-- codigo html para mostrar la tabla que viene desde el controller-->
	<body>
		<jsp:include page="navbar.jsp"/>


		<main class="container">
			<h1>Listado de Producto</h1>
			<section>
				<table class="table">
				  <thead>
				    <tr>
				      <th scope="col" id="id-o">#</th>
				      <th scope="col">C&oacute;digo</th>
				      <th scope="col">T&iacute;tulo</th>
				      <th scope="col">Precio</th>
				      <th scope="col">Fecha Alta</th>
				      <th scope="col">Autor</th>
				      <th scope="col">Imagen</th>
					  <th scope="col">Acciones</th>
				    </tr>
				  </thead>
				  <tbody>
				  <% //scriplet
				  	//en las jsp exixte un objeto llamado request que esta implicito
				  	//capurar/bajar/obtener la lista que guardamos en el controller
				  	List<Producto> listado = (List<Producto>)request.getAttribute("productos");
				  	for(Producto p : listado) {
				  %>
				    <tr>
				      <th scope="row"><%=p.getId()%></th>
                      <td ><%=p.getCodigo()%></td>
                      <td><%=p.getTitulo()%></td>
                      <td><%=p.getPrecio()%></td>
                      <td><%=p.getFechaAlta()%></td>
                      <td><%=p.getAutor()%></td>
                      <td><%=p.getImg()%></td>
					  <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#borrarModal" onclick="setProductoId(<%=p.getId()%>)" > <i class="lni lni-trash-can"> </i> </button>
					      <a class="btn btn-success" role="button" href="<%=request.getContextPath()%>/EditProductController?id=<%=p.getId()%>"> <i class="lni lni-remove-file"></i>  </a></td>
					  
					  <%-- "setProducto(<%=p.getId()%>,<%=p.getCodigo()%>,<%=p.getTitulo()%>,  <%=p.getPrecio()%>,<%=p.getFechaAlta()%>,<%=p.getAutor()%>, <%=p.getImg()%> )" --%>

					  
					  
				    </tr>
				  <%
				  	}
				  %>
				  </tbody>
				</table>
				
			</section>
		</main>

		<%-- <-- ventana modal --> --%>

		

		<jsp:include page = "modalBorrar.jsp"/>

		


		
		
		<script>

			function setProductoId(id) {
				document.getElementById('idProducto').innerHTML= "Confirma borrar id: ";
				document.getElementById('idBorrar').value = id;
			}


			
		</script>
		<jsp:include page = "script.jsp"/>

	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="es.uc3m.tiw.model.Leccion"%>

<%@ page import="es.uc3m.tiw.web.ServletSession"%>
    <%@ page import="javax.servlet.ServletException"%>
    <%@ page import="javax.servlet.annotation.WebServlet"%>
    <%@ page import="javax.servlet.http.HttpServlet"%>
    <%@ page import="javax.servlet.http.HttpServletRequest"%>
    <%@ page import="javax.servlet.http.HttpServletResponse"%>
    <%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html >
   
<html>
<!--Head contenedor del tÃ­tulo de la pÃ¡gina, enlaces a las stylesheets, tipografÃ­as y charset-->
<head>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

  <!-- Jquery para cargar los scripts de bootstrap --> 
  <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
  
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  <title>Catalogo de lecciones</title>
  <meta name="Alex" content="Grupo de practicas TIW" lang="es">
  <link rel="icon" type="image/png" href="./images/icono.jpg"> 


  <link rel="stylesheet" type="text/css" href="./style/styleHome.css">
  <link rel="stylesheet" type="text/css" href="./style/styleFondoBlanco.css">
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script type="text/javascript" src="./script/scriptHome.js"></script>

  <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Raleway:100' rel='stylesheet' type='text/css'>
  <META HTTP-EQUIV="Content-Type" content="text/html; charset=utf-8"/>
</head>

<body>
  <!--Header-->
  	<%
  
  	if (session.getAttribute("usuario") != null) { %>
 	<jsp:include page="HeaderLog.jsp"/> 
	<%}else{%>
	 <jsp:include page="Header.jsp"/> 
	<% } %>
	
	<div id="fondoBlanco" style="margin: 5px">
	<form action="ServletLecciones" enctype="multipart/form-data"
		method="post">
		<hr>
		<fieldset>
			<%
				String id = request.getParameter("id");
			String identificador=request.getParameter("identificador");
			%>
			<input type="hidden" name="id" value="<%=id%>" />

			<legend> Contenido de lecciones </legend>

			<p>
				Descripcion: <input type="text" name="descripcion" />
			</p>
			<br>
			<br>

			<h1> Subir Material</h1>
	
		<input type="hidden" name="action" value="upload">

	 <p>
			<input type="file" name="material">
		</p> 

			<input type="submit" value="Dar de alta la leccion" />
			<ul>
				<%
					if (request.getAttribute("Listalecciones") != null) {
						List<Leccion> ListaLecciones = (List<Leccion>) request.getAttribute("Listalecciones");
						int contador = 0;
						for (Leccion leccion : ListaLecciones) {
				%>
				<li><%=leccion.getDescripcion()%> 
				<a href="ServletImagenes?foto=<%=leccion.getMaterial() %>">Material de la leccion</a><br><br>
				</li>
				<%
					contador++;
				%>
				<%
					}
					}
				%>
				<a href="CatalogoLecciones.jsp?id=<%=request.getParameter("id")%>">
					Añadir otra Leccion al curso </a>

			</ul>

			<a href="PersistenceServletCursos" > Volver a los Cursos</a><br>	
			</fieldset>
	</form>
</div>
    <!--Pie de pÃ¡gina-->
	<%@include file="Footer.jsp"%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="es.uc3m.tiw.model.Usuario"%>
<%@ page import="es.uc3m.tiw.web.ServletRegistroUsuario"%>
<%@ page import="es.uc3m.tiw.web.Curso"%>

 <%@ page import="es.uc3m.tiw.web.ServletSession"%>
    <%@ page import="javax.servlet.ServletException"%>
    <%@ page import="javax.servlet.annotation.WebServlet"%>
    <%@ page import="javax.servlet.http.HttpServlet"%>
    <%@ page import="javax.servlet.http.HttpServletRequest"%>
    <%@ page import="javax.servlet.http.HttpServletResponse"%>
    <%@ page import="javax.servlet.http.HttpSession"%>
    
<!DOCTYPE html >
   
<html>
<!--Head contenedor del título de la página, enlaces a las stylesheets, tipografías y charset-->
<head>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

  <!-- Jquery para cargar los scripts de bootstrap --> 
  <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
  
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  <title>Modificacion perfil de usuario</title>
  <meta name="Alex" content="Grupo de practicas TIW" lang="es">
  <link rel="icon" type="image/png" href="./images/icono.jpg"> 


  <link rel="stylesheet" type="text/css" href="./style/styleHome.css">
   <link rel="stylesheet" type="text/css" href="./style/styleFondoBlanco.css">
    <link rel="stylesheet" type="text/css" href="./style/styleSimulacion.css">
    
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script type="text/javascript" src="./script/scriptHome.js"></script>
  <script type="text/javascript" src="./script/scriptSimulacion.js"></script>

  <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Raleway:100' rel='stylesheet' type='text/css'>
  <META HTTP-EQUIV="Content-Type" content="text/html; charset=utf-8"/>
</head>

<body>
  <!--Header-->
  	<%if (session.getAttribute("usuario") != null) { %>
 	<jsp:include page="HeaderLog.jsp"/>
	<%}else{%>
	<jsp:include page="Header.jsp"/>
	<% } %> 
	
  <div class="row">
    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
      <div id="imagen">
        <figure>
            <img src="./images/user.png" alt="imagen de perfil" width="150" height="150">
        </figure>
          <!-- <button type="button" id="botonSubmit" onclick="editar()" style="color:black">Editar foto</button> -->
         
          <a href="ServletRegistroUsuario?action=modificar">Editar perfil</a>
   
	<!-- 	<input type="hidden" name="action" value="upload">
	 <p>
			<input type="file" name="imagenuri">
		</p>  -->
          
          
          
          
          
          
          
      </div>
    </div>
 <%
		Usuario UsuarioMod = (Usuario)request.getAttribute("UsuarioModificar");
				
			%>
			<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
		      <div class="enlace5">
		        <p>Usuario:<%=UsuarioMod.getUsuario() %></p>
		        <p>Nombre:<%=UsuarioMod.getNombre() %></p>
		        <p>Apellidos: <%=UsuarioMod.getApellidos() %></p>
		        <p>Edad: <%=UsuarioMod.getEdad() %></p>
		        <p>E-mail: <%=UsuarioMod.getEmail() %></p>
		        <p>Tel&eacute;fono: <%=UsuarioMod.getTelefono() %></p>
		        <p>Direcci&oacute;n:<%=UsuarioMod.getDireccion() %> </p>
		        <p>Descripci&oacute;n: <%=UsuarioMod.getDescripcion() %></p>
		        <p>Intereses: <%=UsuarioMod.getIntereses() %></p>
		      </div>
		    </div>     
		  </div> 

  <div id="CursosInscritos">
      <p>Cursos en los que estoy MATRICULADO</p>
      
      <!-- <ul class="enlacesPerfil">
        <li><a class="enlacePerfil" href="../src/error.html">Hml5 avanzado</a></li>
        <li><a class="enlacePerfil" href="../src/error.html">Curso de fotografia</a></li>
        <li><a class="enlacePerfil" href="../src/error.html">Curso de ingles</a></li>
        <li><a class="enlacePerfil" href="../src/error.html">Curso de piragua</a></li>
      
      </ul> -->
       <!--  Añadido para que salgan sus cursos -->
         <ul class="enlacesPerfil">
         
         <% ArrayList<Curso> CursosMatriculados = (ArrayList<Curso>) request.getAttribute("CursosMatriculados");
         if(CursosMatriculados!=null){
         for( Curso CursosMatriculados1 : CursosMatriculados){ %>
        <p>Titulo del CURSO:</p>
        <li> <%=CursosMatriculados1.getTitulo() %> </li>
        <%} }%>

      </ul>
        
  </div>

<%@include file="Footer.jsp"%>

</body>
</html>
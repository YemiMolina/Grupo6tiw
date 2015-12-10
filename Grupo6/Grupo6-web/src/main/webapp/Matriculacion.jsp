<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.util.Iterator"%>
    <%@ page import="es.uc3m.tiw.model.Curso"%>
    <%@ page import="es.uc3m.tiw.model.Vale"%>
    <%@ page import="es.uc3m.tiw.model.Usuario"%>
    <%@ page import="es.uc3m.tiw.web.ServletPago"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pago del curso para matricularse</title>
</head>
<body>
    
    <%int idCursoActual = Integer.parseInt(request.getParameter("id"));%>
   <%double precio = Double.parseDouble( request.getParameter("precio"));%>
    <%List<Curso> CursosMatriculados = (List<Curso>)request.getAttribute("CursosMatriculados");
    List<Vale> ListaValesFinal = (List<Vale>)request.getAttribute("ListaValesFinal");
  %>
  
   <form action="ServletPago"  method="post"> >
    <% for(Vale vale: ListaValesFinal) {%>
    <li>Cantidad del descuento<%=vale.getCantidad() %> <br>
    Fecha de caducidad del descuento:<%=vale.getFechaCaducidad() %><br><br>
    <input type="radio" name="vale" value="cantidad"  />Codigo del decuento<%=vale.getCodigo() %><br>
    </li>
   <%  } 
    %>

    El precio del curso es :
     <%= precio %> <br>
	El precio final del curso con el descuento es :
    <br>
    
    <br><br>
    
      <input type="submit" value="Aplicar el descuento">
      
<!-- Metodo de pago-->

                    <div class="panel panel-default credit-card-box">
                        <div class="panel-heading display-table">
                            <div class="row display-tr">
                                <h3 class="panel-title display-td">Pago</h3>
                                <div class="display-td">
                                    <img class="img-responsive pull-right"
                                        src="http://i76.imgup.net/accepted_c22e0.png">
                                </div>
                            </div>

                            <div class="panel-body">

                                <div class="row">
                                    <div class="col-xs-8">
                                        <div class="form-group">
                                            <label for="cardNumber">N&uacute;mero de tarjeta</label>
                                            <div class="input-group">
                                                <input type="tel" class="form-control" name="numeroTarjeta"
                                                    placeholder="Numero válido de tarjeta"
                                                    autocomplete="cc-number" required autofocus /> <span
                                                    class="input-group-addon"><i
                                                    class="fa fa-credit-card"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-5 col-md-5">
                                        <div class="form-group">
                                            <label for="cardExpiry"><span class="hidden-xs">Expiraci&oacute;n</span><span
                                                class="visible-xs-inline">EXP</span></label> <input type="tel"
                                                class="form-control" name="expiracion" placeholder="MM / YY"
                                                autocomplete="cc-exp" required />
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-md-3 pull-left">
                                        <div class="form-group">
                                            <label for="cardCVC">C&oacute;digo CVC</label> <input
                                                type="tel" class="form-control" name="codigoCVC"
                                                placeholder="CVC" autocomplete="cc-csc" required />
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="row" style="display: none;">
                                <div class="col-xs-8">
                                    <p class="payment-errors"></p>
                                </div>
                            </div>

                        </div>
                        </div>

    <%-- <a href="ServletPago?id=<%=request.getParameter("id")%>"> --%>
    <a href="Perfil.jsp?">  Finalizar pago </a><br>
    
    
</body>
</html>
<%
   if(session.getAttribute("logueado") != "ok"){
       response.sendRedirect("login.jsp");
   }
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.peliculas" %>
<%@page import="com.emergentes.controlador.MainController" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    peliculas pelis = (peliculas)request.getAttribute("peliculas");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${pelis.id == 0}"> Nuevo  </c:if>
            <c:if test="${pelis.id != 0}"> Editar </c:if>
        </h1>
        <form action="MainController" method="post">
            <table boder="1">
                <input type="hidden" name="id" value="${item.id}" >
                <tr>
                    <td>FECHA</td>
                    <td><input type="text" name="fecha" value="${item.fecha}" ></td>
                </tr>
                <tr>
                    <td>TITULO</td>
                    <td><input type="text" name="titulo" value="${item.titulo}" ></td>
                </tr>
                <tr>
                    <td>CONTENIDO</td>
                    <td><input type="text" name="contenido" value="${item.contenido}" ></td>
                </tr>
                <tr>
                    <td>AUTOR</td>
                    <td><input type="text" name="autor" value="${item.autor}" ></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar" ></td>
                </tr>
            </table>
            
        </form>
    </body>
</html>
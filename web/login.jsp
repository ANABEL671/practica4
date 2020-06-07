<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        
    </head>
    <body>
        <div class="container col-lg-3 text-center" >
            <form action="loginControlador" method="POST">
                <div class="form-group">
                    <img src="img/img1.jpg" heigth="80" width="80" />
                    <p><strong>BIENVENIDOS  AL  SISTEMA  LOGIN</strong></p>
                </div>
                <div class="form-group text-center">
                    <label>Usuario</label>
                    <input class="from-control" type="text" name="usuario" placeholder="Usuario" >
                </div>
                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" name="password" placeholder="Password" class="form-control" >
                </div>
                <input class="btn btn-danger btn-block" type="submit" name="action" value="Ingresar"  >
            </form>
            
        </div>
    </body>
</html>


<%@page import="modelo.Usuario"%>
<%@page import="modelo.Venta"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Listado de Ventas</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <% 
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
        if (usuario == null){
            response.sendRedirect("index.jsp");
        }
    %>
    
    <div class="container mt-4">
        <h1 class="h3">Sistema de Gestión de Ventas</h1>
        <div class="d-flex justify-content-between mb-3">
            <form class="d-flex" action="ControladorVenta" method="POST">
                <button type="submit" class="btn btn-outline-primary" value="listar" name="accion">Listar</button>
                <a class="btn btn-outline-primary ms-2" href="ControladorVenta?accion=nuevo">Agregar Venta</a>
                <a class="btn btn-outline-primary ms-2" href="Controlador?accion=listar">Volver</a>
                <a class="btn btn-outline-primary ms-2" href="cerrarSesion">Salir</a>
            </form>
        </div>
        <hr>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Número de Factura</th>
                    <th scope="col">Cliente ID</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Total</th>
                    <th scope="col">Forma de Pago</th>
                    <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="venta" items="${ventas}">
                    <tr>
                        <th scope="row">${venta.id}</th>
                        <td>${venta.numeroFactura}</td>
                        <td>${venta.clienteId}</td>
                        <td><fmt:formatDate value="${venta.fecha}" pattern="dd/MM/yyyy"/></td>
                        <td>${venta.total}</td>
                        <td>${venta.formaPago}</td>
                        <td>
                            <a href="ControladorVenta?accion=Editar&id=${venta.id}" class="btn btn-outline-warning">Editar</a>
                            <a href="ControladorVenta?accion=Delete&id=${venta.id}" class="btn btn-outline-danger">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
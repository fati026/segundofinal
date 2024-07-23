<%@page import="modelo.Usuario"%>
<%@page import="modelo.Compra"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Listado de Compras</title>
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
    <h1 class="h3">Sistema de Gestión de Compras</h1>
    <div class="d-flex justify-content-between mb-3">
        <form class="d-flex" action="ControladorCompra" method="POST">
            <button type="submit" class="btn btn-outline-primary" value="listar" name="accion">Listar</button>
            <a class="btn btn-outline-primary ms-2" href="ControladorCompra?accion=nuevo">Agregar Compra</a>
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
                <th scope="col">Proveedor ID</th>
                <th scope="col">Fecha</th>
                <th scope="col">Total</th>
                <th scope="col">Forma de Pago</th>
                <th scope="col">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="compra" items="${compras}">
                <tr>
                    <th scope="row">${compra.id}</th>
                    <td>${compra.numeroFactura}</td>
                    <td>${compra.proveedorId}</td>
                    <td><fmt:formatDate value="${compra.fecha}" pattern="dd/MM/yyyy"/></td>
                    <td>${compra.total}</td>
                    <td>${compra.formaPago}</td>
                    <td>
                        <a href="ControladorCompra?accion=Editar&id=${compra.id}" class="btn btn-outline-warning">Editar</a>
                        <a href="ControladorCompra?accion=Delete&id=${compra.id}" class="btn btn-outline-danger">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>

<%@page import="modelo.Usuario"%>
<%@page import="modelo.CompraDetalle"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Listado de Detalles de Compra</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <% 
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
        if (usuario == null) {
            response.sendRedirect("index.jsp");
        }
    %>
    
    <div class="container mt-4">
        <h1 class="h3">Sistema de Gestión de Detalle de Compras </h1>
        <div class="d-flex justify-content-between mb-3">
            <form class="d-flex" action="ControladorCompraDetalle" method="POST">
                <button type="submit" class="btn btn-outline-primary" value="listar" name="accion">Listar</button>
                <a class="btn btn-outline-primary ms-2" href="ControladorCompraDetalle?accion=nuevo">Agregar Detalle de Compra</a>
                <a class="btn btn-outline-primary ms-2" href="Controlador?accion=listar">Volver</a>
                <a class="btn btn-outline-primary ms-2" href="cerrarSesion">Salir</a>
            </form>
        </div>
        <hr>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">ID de Compra</th>
                    <th scope="col">ID de Producto</th>
                    <th scope="col">Cantidad</th>
                    <th scope="col">Precio Unitario</th>
                    <th scope="col">Total del Artículo</th>
                    <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="detalle" items="${detalles}">
                    <tr>
                        <th scope="row">${detalle.id}</th>
                        <td>${detalle.compraId}</td>
                        <td>${detalle.productoId}</td>
                        <td>${detalle.cantidad}</td>
                        <td>${detalle.precioUnitario}</td>
                        <td>${detalle.totalArticulo}</td>
                        <td>
                            <a href="ControladorCompraDetalle?accion=Editar&id=${detalle.id}" class="btn btn-outline-warning">Editar</a>
                            <a href="ControladorCompraDetalle?accion=Delete&id=${detalle.id}" class="btn btn-outline-danger">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
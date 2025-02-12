<%@page import="modelo.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Listado de Proveedores</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/css.css" rel="stylesheet">
</head>
<body>

<% 
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");

    if (usuario == null){
        response.sendRedirect("index.jsp");
    }
    else if (usuario.getAdministrador() == 0){
        response.sendRedirect("listadoProductos.jsp");
    }
%>

<div class="container mt-4">
    <h1 class="h3">Proveedores del sistema</h1>
    <div class="d-flex justify-content-between">
        <div class="d-flex">
            <a class="btn btn-outline-danger" href="ControladorProveedores?accion=listar">Listar</a>
            <a class="btn btn-outline-danger ms-2" href="ControladorProveedores?accion=nuevo">Agregar</a>
            <a class="btn btn-outline-danger ms-2" href="Controlador?accion=listar">Volver</a>
            <a class="btn btn-outline-danger ms-2" href="cerrarSesion">SALIR</a>
        </div>
    </div>
    <hr>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nombre Proveedor</th>
                <th scope="col">Correo Electrónico</th>
                <th scope="col">Teléfono</th>
                <th scope="col">Dirección</th>
                <th scope="col">RUC</th> 
                <th scope="col">Acciones</th>
            </tr>
        </thead>
        <tbody class="table-group-divider">
            <c:forEach var="proveedor" items="${Proveedores}">
                <tr>
                    <th scope="row">${proveedor.id}</th>
                    <td>${proveedor.nombreProveedor}</td>
                    <td>${proveedor.correoElectronico}</td>
                    <td>${proveedor.telefono}</td>
                    <td>${proveedor.direccion}</td>
                    <td>${proveedor.ruc}</td> 
                    <td>
                        <a href="ControladorProveedores?accion=editar&id=${proveedor.id}" class="btn btn-outline-warning">Editar</a>
                        <a href="ControladorProveedores?accion=eliminar&id=${proveedor.id}" class="btn btn-outline-danger">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>

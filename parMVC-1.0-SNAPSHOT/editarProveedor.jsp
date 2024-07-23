<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Objects"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema de Gestión - Editar Proveedor</title>
        <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
            <form action="ControladorProveedores" method="POST">
                <div class="card border-info mb-4" style="width: 18rem;">
                    <div class="card-header">
                        ACTUALIZAR PROVEEDOR
                    </div>

                    <div class="card-body text-info">
                        <div class="form-group">
                            <label>ID</label>
                            <input type="text" value="${proveedor.id}" name="txtId" readonly class="form-control">
                        </div>
                        <div class="form-group">
                            <label>NOMBRE</label>
                            <input type="text" value="${proveedor.nombreProveedor}" name="txtNombreProveedor" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>CORREO ELECTRÓNICO</label>
                            <input type="email" value="${proveedor.correoElectronico}" name="txtCorreoElectronico" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>TELÉFONO</label>
                            <input type="text" value="${proveedor.telefono}" name="txtTelefono" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>DIRECCIÓN</label>
                            <input type="text" value="${proveedor.direccion}" name="txtDireccion" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>RUC</label>
                            <input type="text" value="${proveedor.ruc}" name="txtRuc" class="form-control" required>
                        </div>
                    </div>
                    <div class="card-footer">
                        <input type="submit" value="Actualizar" name="accion" class="btn btn-outline-success">
                        <a href="ControladorProveedores?accion=listar" class="btn-link ms-2">Volver</a>
                    </div>
                </div>
            </form>
        </div>
        <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
    </body>
</html>

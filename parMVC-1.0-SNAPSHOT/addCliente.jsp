<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Agregar Cliente</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <% HttpSession sesion = request.getSession(); Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado"); if (usuario == null){ response.sendRedirect("index.jsp"); } else if (usuario.getAdministrador() == 0){ response.sendRedirect("listadoProductos.jsp"); } %>
    <div class="container mt-4">
        <form action="ControladorClientes" method="POST">
            <div class="card border-info mb-4" style="width: 18rem;">
                <div class="card-header">AGREGAR CLIENTE</div>
                <div class="card-body text-info">
                    <div class="form-group">
                        <label>NOMBRE</label>
                        <input type="text" name="txtNombre" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>APELLIDO</label>
                        <input type="text" name="txtApellido" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>CORREO ELECTRÓNICO</label>
                        <input type="email" name="txtCorreoElectronico" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>TELÉFONO</label>
                        <input type="text" name="txtTelefono" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>DIRECCIÓN</label>
                        <input type="text" name="txtDireccion" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>CEDULA</label>
                        <input type="text" name="txtCedula" class="form-control">
                    </div>
                </div>
                <div class="card-footer">
                    <input type="submit" value="Agregar" name="accion" class="btn btn-outline-success">
                    <a href="ControladorClientes?accion=listar" class="btn-link ms-2">Volver</a>
                </div>
            </div>
        </form>
    </div>
    <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
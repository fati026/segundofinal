<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Agregar compra</title>
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
    <form action="ControladorCompra" method="POST">
        <div class="card border-info mb-4">
            <div class="card-header">
                AGREGAR COMPRA
            </div>

            <div class="card-body text-info">
                <div class="form-group">
                    <label for="txtNumeroFactura">Número de Factura</label>
                    <input type="text" name="txtNumeroFactura" id="txtNumeroFactura" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="txtProveedorId">Proveedor</label>
                    <input type="text" name="txtProveedorId" id="txtProveedorId" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="txtFecha">Fecha</label>
                    <input type="text" name="txtFecha" id="txtFecha" class="form-control" required placeholder="dd/MM/yyyy">
                </div>

                <div class="form-group">
                    <label for="txtTotal">Total</label>
                    <input type="number" step="0.01" name="txtTotal" id="txtTotal" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="txtFormaPago">Forma de Pago</label>
                    <input type="text" name="txtFormaPago" id="txtFormaPago" class="form-control">
                </div>
            </div>

            <div class="card-footer">
                <input type="submit" value="Agregar" name="accion" class="btn btn-outline-success">
                <a href="ControladorCompra?accion=listar" class="btn-link ms-2">Volver</a>
            </div>
        </div>
    </form>
</div>
<script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>

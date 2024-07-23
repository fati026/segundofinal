<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Agregar Detalle de Venta</title>
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
        <form action="ControladorVentaDetalle" method="POST">
            <div class="card border-info mb-4">
                <div class="card-header">
                    AGREGAR DETALLE DE VENTA
                </div>
                <div class="card-body text-info">
                    <div class="form-group">
                        <label for="txtVentaId">ID de Venta</label>
                        <input type="text" name="txtVentaId" id="txtVentaId" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="txtProductoId">ID de Producto</label>
                        <input type="text" name="txtProductoId" id="txtProductoId" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="txtCantidad">Cantidad</label>
                        <input type="number" name="txtCantidad" id="txtCantidad" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="txtPrecioUnitario">Precio Unitario</label>
                        <input type="number" step="0.01" name="txtPrecioUnitario" id="txtPrecioUnitario" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="txtTotalArticulo">Total del Artículo</label>
                        <input type="number" step="0.01" name="txtTotalArticulo" id="txtTotalArticulo" class="form-control" required>
                    </div>
                </div>
                <div class="card-footer">
                    <input type="submit" value="Agregar" name="accion" class="btn btn-outline-success">
                    <a href="ControladorVentaDetalle?accion=listar" class="btn-link ms-2">Volver</a>
                </div>
            </div>
        </form>
    </div>
    
    <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
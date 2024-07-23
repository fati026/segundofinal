<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.CompraDetalle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Editar Detalle de Compra</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <% 
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
        if (usuario == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        // Obtener el detalle de compra a editar desde el atributo de la solicitud
        CompraDetalle detalle = (CompraDetalle) request.getAttribute("detalle");
        if (detalle == null) {
            response.sendRedirect("ControladorCompraDetalle?accion=listar");
            return;
        }
    %>
    
    <div class="container mt-4">
        <form action="ControladorCompraDetalle" method="POST">
            <div class="card border-info mb-4">
                <div class="card-header">
                    ACTUALIZAR DETALLE DE COMPRA
                </div>
                <div class="card-body text-info">
                    <div class="form-group">
                        <label>ID</label>
                        <input type="text" value="<c:out value='${detalle.id}' />" name="txtId" readonly class="form-control">
                    </div>
                    <div class="form-group">
                        <label>ID de Compra</label>
                        <input type="text" value="<c:out value='${detalle.compraId}' />" name="txtCompraId" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>ID de Producto</label>
                        <input type="text" value="<c:out value='${detalle.productoId}' />" name="txtProductoId" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Cantidad</label>
                        <input type="number" value="<c:out value='${detalle.cantidad}' />" name="txtCantidad" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Precio Unitario</label>
                        <input type="number" step="0.01" value="<c:out value='${detalle.precioUnitario}' />" name="txtPrecioUnitario" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Total del Artículo</label>
                        <input type="number" step="0.01" value="<c:out value='${detalle.totalArticulo}' />" name="txtTotalArticulo" class="form-control" required>
                    </div>
                </div>
                <div class="card-footer">
                    <input type="submit" value="Actualizar" name="accion" class="btn btn-outline-success">
                    <a href="ControladorCompraDetalle?accion=listar" class="btn-link ms-2">Volver</a>
                </div>
            </div>
        </form>
    </div>
    
    <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
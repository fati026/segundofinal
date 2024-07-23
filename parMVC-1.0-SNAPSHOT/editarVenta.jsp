<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Venta"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Editar Venta</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <% 
        // Obtener la sesión actual y el usuario logueado
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
        if (usuario == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    
    <% 
        // Obtener la venta a editar desde el atributo de la solicitud
        Venta venta = (Venta) request.getAttribute("venta");
        if (venta == null) {
            response.sendRedirect("ControladorVenta?accion=listar");
            return;
        }
        
        // Formatear la fecha en el formato deseado (dd/MM/yyyy)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = sdf.format(venta.getFecha());
    %>
    
    <div class="container mt-4">
        <form action="ControladorVenta" method="POST">
            <div class="card border-info mb-4">
                <div class="card-header">
                    ACTUALIZAR VENTA
                </div>
                <div class="card-body text-info">
                    <div class="form-group">
                        <label>ID</label>
                        <input type="text" value="<c:out value='${venta.id}' />" name="txtId" readonly class="form-control">
                    </div>
                    
                    <div class="form-group">
                        <label>Número de Factura</label>
                        <input type="text" value="<c:out value='${venta.numeroFactura}' />" name="txtNumeroFactura" class="form-control" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Cliente ID</label>
                        <input type="text" value="<c:out value='${venta.clienteId}' />" name="txtClienteId" class="form-control" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Fecha</label>
                        <input type="text" value="<%= fechaFormateada %>" name="txtFecha" class="form-control" required placeholder="dd/MM/yyyy">
                    </div>
                    
                    <div class="form-group">
                        <label>Total</label>
                        <input type="number" step="0.01" value="<c:out value='${venta.total}' />" name="txtTotal" class="form-control" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Forma de Pago</label>
                        <input type="text" value="<c:out value='${venta.formaPago}' />" name="txtFormaPago" class="form-control">
                    </div>
                </div>
                <div class="card-footer">
                    <input type="submit" value="Actualizar" name="accion" class="btn btn-outline-success">
                    <a href="ControladorVenta?accion=listar" class="btn-link ms-2">Volver</a>
                </div>
            </div>
        </form>
    </div>
    
    <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
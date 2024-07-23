package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.VentaDetalle;
import modeloDAO.VentaDetalleDAO;

public class ControladorVentaDetalle extends HttpServlet {
    private VentaDetalleDAO daoVentaDetalle;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        List<VentaDetalle> detalles = new ArrayList<>();
        daoVentaDetalle = new VentaDetalleDAO();

        try {
            switch (accion) {
                case "listar":
                    detalles = daoVentaDetalle.getDetalles(); // Obtener todos los detalles de venta
                    Collections.reverse(detalles); // Opcional: invertir el orden
                    request.setAttribute("detalles", detalles);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("listadoVentaDetalle.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "nuevo":
                    request.getRequestDispatcher("addVentaDetalle.jsp").forward(request, response);
                    break;

                case "Agregar":
                    // Obtener parámetros del formulario
                    Integer ventaId = Integer.valueOf(request.getParameter("txtVentaId"));
                    Integer productoId = Integer.valueOf(request.getParameter("txtProductoId"));
                    Integer cantidad = Integer.valueOf(request.getParameter("txtCantidad"));
                    Double precioUnitario = Double.valueOf(request.getParameter("txtPrecioUnitario"));
                    Double totalArticulo = Double.valueOf(request.getParameter("txtTotalArticulo"));

                    // Crear objeto de detalle de venta
                    VentaDetalle detalle = new VentaDetalle(ventaId, productoId, cantidad, precioUnitario, totalArticulo);
                    int resultado = daoVentaDetalle.add(detalle); // Método para agregar el detalle

                    // Mensaje de éxito o error
                    if (resultado != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "EL DETALLE DE VENTA SE HA AGREGADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO GUARDAR EL DETALLE DE VENTA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    VentaDetalle detalleEditar = daoVentaDetalle.getId(id); // Obtener detalle por ID
                    request.setAttribute("detalle", detalleEditar);
                    request.getRequestDispatcher("editarVentaDetalle.jsp").forward(request, response);
                    break;

                case "Actualizar":
                    int idDetalle = Integer.parseInt(request.getParameter("txtId"));
                    ventaId = Integer.parseInt(request.getParameter("txtVentaId"));
                    productoId = Integer.parseInt(request.getParameter("txtProductoId"));
                    cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    precioUnitario = Double.parseDouble(request.getParameter("txtPrecioUnitario"));
                    totalArticulo = Double.parseDouble(request.getParameter("txtTotalArticulo"));

                    // Crear objeto de detalle de venta para actualizar
                    VentaDetalle detalleActualizar = new VentaDetalle(ventaId, productoId, cantidad, precioUnitario, totalArticulo);
                    detalleActualizar.setId(idDetalle); // Establecer el ID del detalle a actualizar

                    int respuesta = daoVentaDetalle.update(detalleActualizar); // Método para actualizar el detalle
                    if (respuesta != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "EL DETALLE DE VENTA SE HA ACTUALIZADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ACTUALIZAR EL DETALLE DE VENTA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Delete":
                    int idVentaDetalle = Integer.parseInt(request.getParameter("id"));
                    int res = daoVentaDetalle.delete(idVentaDetalle); // Método para eliminar el detalle
                    if (res != 0) {
                        request.setAttribute("config", "alert alert-warning");
                        request.setAttribute("mensaje", "EL DETALLE DE VENTA SE HA ELIMINADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR EL DETALLE DE VENTA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                default:
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "Acción no válida");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("config", "alert alert-danger");
            request.setAttribute("mensaje", "Error al procesar datos numéricos: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("config", "alert alert-danger");
            request.setAttribute("mensaje", e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("config", "alert alert-danger");
            request.setAttribute("mensaje", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestionar detalles de venta";
    }
}
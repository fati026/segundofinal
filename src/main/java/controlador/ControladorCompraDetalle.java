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
import modelo.CompraDetalle;
import modeloDAO.CompraDetalleDAO;

public class ControladorCompraDetalle extends HttpServlet {
    private CompraDetalleDAO daoCompraDetalle;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        List<CompraDetalle> detalles = new ArrayList<>();
        daoCompraDetalle = new CompraDetalleDAO();

        try {
            switch (accion) {
                case "listar":
                    detalles = daoCompraDetalle.getDetalles();
                    Collections.reverse(detalles); // Opcional: invertir el orden
                    request.setAttribute("detalles", detalles);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("listadoCompraDetalle.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "nuevo":
                    request.getRequestDispatcher("addCompraDetalle.jsp").forward(request, response);
                    break;

                case "Agregar":
                    // Obtener parámetros del formulario
                    Integer compraId = Integer.valueOf(request.getParameter("txtCompraId"));
                    Integer productoId = Integer.valueOf(request.getParameter("txtProductoId"));
                    Integer cantidad = Integer.valueOf(request.getParameter("txtCantidad"));
                    Double precioUnitario = Double.valueOf(request.getParameter("txtPrecioUnitario"));
                    Double totalArticulo = Double.valueOf(request.getParameter("txtTotalArticulo"));

                    // Crear objeto de detalle de compra
                    CompraDetalle detalle = new CompraDetalle(compraId, productoId, cantidad, precioUnitario, totalArticulo);
                    int resultado = daoCompraDetalle.add(detalle); // Método para agregar el detalle

                    // Mensaje de éxito o error
                    if (resultado != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "EL DETALLE DE COMPRA SE HA AGREGADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO GUARDAR EL DETALLE DE COMPRA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    CompraDetalle detalleEditar = daoCompraDetalle.getId(id); // Obtener detalle por ID
                    request.setAttribute("detalle", detalleEditar);
                    request.getRequestDispatcher("editarCompraDetalle.jsp").forward(request, response);
                    break;

                case "Actualizar":
                    int idDetalle = Integer.parseInt(request.getParameter("txtId"));
                    compraId = Integer.parseInt(request.getParameter("txtCompraId"));
                    productoId = Integer.parseInt(request.getParameter("txtProductoId"));
                    cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    precioUnitario = Double.parseDouble(request.getParameter("txtPrecioUnitario"));
                    totalArticulo = Double.parseDouble(request.getParameter("txtTotalArticulo"));

                    // Crear objeto de detalle de compra para actualizar
                    CompraDetalle detalleActualizar = new CompraDetalle(compraId, productoId, cantidad, precioUnitario, totalArticulo);
                    detalleActualizar.setId(idDetalle); // Establecer el ID del detalle a actualizar

                    int respuesta = daoCompraDetalle.update(detalleActualizar); // Método para actualizar el detalle
                    if (respuesta != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "EL DETALLE DE COMPRA SE HA ACTUALIZADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ACTUALIZAR EL DETALLE DE COMPRA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Delete":
                    int idCompraDetalle = Integer.parseInt(request.getParameter("id"));
                    int res = daoCompraDetalle.delete(idCompraDetalle); // Método para eliminar el detalle
                    if (res != 0) {
                        request.setAttribute("config", "alert alert-warning");
                        request.setAttribute("mensaje", "EL DETALLE DE COMPRA SE HA ELIMINADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR EL DETALLE DE COMPRA");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Controlador para gestionar detalles de compra";
    }
}
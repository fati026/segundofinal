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
import modelo.Venta;
import modeloDAO.VentaDAO;

public class ControladorVenta extends HttpServlet {
    private VentaDAO daoVenta;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        List<Venta> ventas = new ArrayList<>();
        daoVenta = new VentaDAO();

        try {
            switch (accion) {
                case "listar":
                    ventas = daoVenta.getVentas();
                    Collections.reverse(ventas); // Opcional: invertir el orden
                    request.setAttribute("ventas", ventas);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("listadoVenta.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "nuevo":
                    request.getRequestDispatcher("addVenta.jsp").forward(request, response);
                    break;

                case "Agregar":
                    String numeroFactura = request.getParameter("txtNumeroFactura");
                    String clienteIdStr = request.getParameter("txtClienteId");
                    String fechaStr = request.getParameter("txtFecha");
                    String totalStr = request.getParameter("txtTotal");
                    String formaPago = request.getParameter("txtFormaPago");

                    if (numeroFactura == null || numeroFactura.isEmpty() || clienteIdStr == null || clienteIdStr.isEmpty() || fechaStr == null || fechaStr.isEmpty() || totalStr == null || totalStr.isEmpty() || formaPago == null || formaPago.isEmpty()) {
                        throw new IllegalArgumentException("Todos los campos son obligatorios.");
                    }

                    Integer clienteId = Integer.valueOf(clienteIdStr);
                    Double total = Double.valueOf(totalStr);
                    java.sql.Date fecha;

                    try {
                        fecha = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr).getTime());
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Formato de fecha inválido.");
                    }

                    Venta venta = new Venta(numeroFactura, clienteId, fecha, total, formaPago);
                    int resultado = daoVenta.add(venta);

                    if (resultado != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "LA VENTA SE HA AGREGADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO GUARDAR LA VENTA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Venta v = daoVenta.getId(id);
                    request.setAttribute("venta", v);
                    request.getRequestDispatcher("editarVenta.jsp").forward(request, response);
                    break;

                case "Actualizar":
                    int idVenta = Integer.parseInt(request.getParameter("txtId"));
                    String numeroFactura1 = request.getParameter("txtNumeroFactura");
                    String clienteIdStr1 = request.getParameter("txtClienteId");
                    String fechaStr1 = request.getParameter("txtFecha");
                    String totalStr1 = request.getParameter("txtTotal");
                    String formaPago1 = request.getParameter("txtFormaPago");

                    Integer clienteId1 = Integer.valueOf(clienteIdStr1);
                    Double total1 = Double.valueOf(totalStr1);
                    java.sql.Date fecha1;

                    try {
                        fecha1 = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr1).getTime());
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Formato de fecha inválido.");
                    }

                    Venta ventaEditada = new Venta(idVenta, numeroFactura1, clienteId1, fecha1, total1, formaPago1);
                    int respuesta = daoVenta.update(ventaEditada);

                    if (respuesta != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "LA VENTA SE HA ACTUALIZADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ACTUALIZAR LA VENTA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Delete":
                    int idVentaDelete = Integer.parseInt(request.getParameter("id"));
                    int res = daoVenta.delete(idVentaDelete);
                    if (res != 0) {
                        request.setAttribute("config", "alert alert-warning");
                        request.setAttribute("mensaje", "LA VENTA SE HA ELIMINADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR LA VENTA");
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
        return "Controlador para gestionar ventas";
    }
}
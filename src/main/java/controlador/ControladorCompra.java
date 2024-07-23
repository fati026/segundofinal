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
import modelo.Compra;
import modeloDAO.CompraDAO;

public class ControladorCompra extends HttpServlet {

    private CompraDAO daoCompra;

    // Método para filtrar compras por proveedor
    private List<Compra> filtrarPorProveedor(List<Compra> compras, Integer proveedorAFiltrar) {
        List<Compra> comprasFiltradas = new ArrayList<>();
        for (Compra compra : compras) {
            if (Objects.equals(compra.getProveedorId(), proveedorAFiltrar)) {
                comprasFiltradas.add(compra);
            }
        }
        return comprasFiltradas;
    }

    // Método principal para manejar las solicitudes
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        List<Compra> compras = new ArrayList<>();
        HttpSession session = request.getSession();
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        daoCompra = new CompraDAO();

        try {
            switch (accion) {
                case "listar":
                    compras = daoCompra.getCompras();
                    Collections.reverse(compras);

                    String proveedorStr = request.getParameter("txtProveedor");
                    Integer proveedorAFiltrar = null;

                    if (proveedorStr != null && !proveedorStr.isEmpty()) {
                        try {
                            proveedorAFiltrar = Integer.valueOf(proveedorStr);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Número de proveedor no válido.");
                        }
                    }

                    if (proveedorAFiltrar == null || proveedorAFiltrar == 0) {
                        request.setAttribute("compras", compras);  // Asegúrate de que el nombre coincide con el JSP
                    } else {
                        compras = filtrarPorProveedor(compras, proveedorAFiltrar);
                        request.setAttribute("compras", compras);  // Asegúrate de que el nombre coincide con el JSP
                    }

                    // Redirige a la vista JSP para listar las compras
                    RequestDispatcher dispatcher = request.getRequestDispatcher("listadoCompra.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "nuevo":
                    request.getRequestDispatcher("addCompra.jsp").forward(request, response);
                    break;

                case "Agregar":
                    // Imprimir los parámetros para depuración
                    System.out.println("Número de Factura: " + request.getParameter("txtNumeroFactura"));
                    System.out.println("Proveedor: " + request.getParameter("txtProveedorId"));
                    System.out.println("Fecha: " + request.getParameter("txtFecha"));
                    System.out.println("Total: " + request.getParameter("txtTotal"));
                    System.out.println("Forma de Pago: " + request.getParameter("txtFormaPago"));

                    String numeroFactura = request.getParameter("txtNumeroFactura");
                    String proveedorIdStr = request.getParameter("txtProveedorId");
                    String fechaStr = request.getParameter("txtFecha");
                    String totalStr = request.getParameter("txtTotal");
                    String formaPago = request.getParameter("txtFormaPago");

                    if (numeroFactura == null || numeroFactura.isEmpty() ||
                        proveedorIdStr == null || proveedorIdStr.isEmpty() ||
                        fechaStr == null || fechaStr.isEmpty() ||
                        totalStr == null || totalStr.isEmpty() ||
                        formaPago == null || formaPago.isEmpty()) {
                        throw new IllegalArgumentException("Todos los campos son obligatorios.");
                    }

                    Integer proveedorId = Integer.valueOf(proveedorIdStr);
                    Double total = Double.valueOf(totalStr);

                    java.sql.Date fecha;
                    try {
                        fecha = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr).getTime());
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Formato de fecha inválido.");
                    }

                    Compra compra = new Compra(numeroFactura, proveedorId, fecha, total, formaPago);
                    int resultado = daoCompra.add(compra);
                    if (resultado != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "LA COMPRA SE HA AGREGADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO GUARDAR LA COMPRA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Compra c = daoCompra.getId(id);
                    request.setAttribute("compra", c);
                    request.getRequestDispatcher("editarCompra.jsp").forward(request, response);
                    break;

                case "Actualizar":
                    int idCompra = Integer.parseInt(request.getParameter("txtId"));
                    String numeroFactura1 = request.getParameter("txtNumeroFactura");
                    String proveedorIdStr1 = request.getParameter("txtProveedorId");
                    String fechaStr1 = request.getParameter("txtFecha");
                    String totalStr1 = request.getParameter("txtTotal");
                    String formaPago1 = request.getParameter("txtFormaPago");

                    Integer proveedorId1 = Integer.valueOf(proveedorIdStr1);
                    Double total1 = Double.valueOf(totalStr1);

                    java.sql.Date fecha1;
                    try {
                        fecha1 = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr1).getTime());
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Formato de fecha inválido.");
                    }

                    Compra compraEditada = new Compra(idCompra, numeroFactura1, proveedorId1, fecha1, total1, formaPago1);
                    int respuesta = daoCompra.update(compraEditada);
                    if (respuesta != 0) {
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "LA COMPRA SE HA ACTUALIZADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ACTUALIZAR LA COMPRA");
                    }
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    break;

                case "Delete":
                    int idComp = Integer.parseInt(request.getParameter("id"));
                    int res = daoCompra.delete(idComp);
                    if (res != 0) {
                        request.setAttribute("config", "alert alert-warning");
                        request.setAttribute("mensaje", "LA COMPRA SE HA ELIMINADO CON ÉXITO");
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR LA COMPRA");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestionar compras";
    }
}

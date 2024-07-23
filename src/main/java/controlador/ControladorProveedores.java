package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Proveedor;
import modeloDAO.ProveedorDAO;

public class ControladorProveedores extends HttpServlet {

    ProveedorDAO DaoProveedor;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        List<Proveedor> proveedores = new ArrayList<>();
        HttpSession session = request.getSession();
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        DaoProveedor = new ProveedorDAO(); // Inicializar DaoProveedor aquí

        switch (accion) {
            case "listar":
                proveedores = DaoProveedor.getProveedores();
                request.setAttribute("Proveedores", proveedores);
                request.getRequestDispatcher("listadoProveedores.jsp").forward(request, response);
                break;
            case "nuevo":
                request.getRequestDispatcher("addProveedor.jsp").forward(request, response);
                break;
            case "Agregar":
                int resultado;
                String nombreProveedor = request.getParameter("txtNombreProveedor");
                String correoElectronico = request.getParameter("txtCorreoElectronico");
                String telefono = request.getParameter("txtTelefono");
                String direccion = request.getParameter("txtDireccion");
                String ruc = request.getParameter("txtRuc"); // Añadir ruc
                Proveedor proveedor = new Proveedor(nombreProveedor, correoElectronico, telefono, direccion, ruc);
                resultado = DaoProveedor.add(proveedor);
                if (resultado != 0) {
                    request.setAttribute("config", "alert alert-success");
                    request.setAttribute("mensaje", "EL PROVEEDOR SE HA AGREGADO CON ÉXITO");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                } else {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "NO SE HA PODIDO GUARDAR EL PROVEEDOR");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            case "editar":
                int idProveedor = Integer.valueOf(request.getParameter("id"));
                Proveedor p = DaoProveedor.getId(idProveedor);
                request.setAttribute("proveedor", p);
                request.getRequestDispatcher("editarProveedor.jsp").forward(request, response);
                break;
            case "Actualizar":
                int idProveedorToUpdate = Integer.valueOf(request.getParameter("txtId"));
                String nombreProveedorToUpdate = request.getParameter("txtNombreProveedor");
                String correoElectronicoToUpdate = request.getParameter("txtCorreoElectronico");
                String telefonoToUpdate = request.getParameter("txtTelefono");
                String direccionToUpdate = request.getParameter("txtDireccion");
                String rucToUpdate = request.getParameter("txtRuc"); // Añadir ruc
                Proveedor proveedorToUpdate = new Proveedor(idProveedorToUpdate, nombreProveedorToUpdate, correoElectronicoToUpdate, telefonoToUpdate, direccionToUpdate, rucToUpdate);
                int respuesta = DaoProveedor.update(proveedorToUpdate);
                if (respuesta != 0) {
                    request.setAttribute("config", "alert alert-success");
                    request.setAttribute("mensaje", "EL PROVEEDOR SE HA ACTUALIZADO CON ÉXITO");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                } else {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "NO SE HA PODIDO ACTUALIZAR EL PROVEEDOR");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            case "eliminar":
                int idProveedorToDelete = Integer.valueOf(request.getParameter("id"));
                int res = DaoProveedor.delete(idProveedorToDelete);
                if (res != 0) {
                    request.setAttribute("config", "alert alert-warning");
                    request.setAttribute("mensaje", "EL PROVEEDOR SE HA ELIMINADO CON ÉXITO");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                } else {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR EL PROVEEDOR");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            default:
                throw new AssertionError();
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
        return "Short description";
    }

}

package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modeloDAO.ClienteDAO;

public class ControladorClientes extends HttpServlet {
    private ClienteDAO clienteDAO;

    public ControladorClientes() {
        this.clienteDAO = new ClienteDAO();
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

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        switch (accion) {
            case "listar":
                List<Cliente> clientes = clienteDAO.getClientes();
                request.setAttribute("Clientes", clientes);
                request.getRequestDispatcher("listadoClientes.jsp").forward(request, response);
                break;
            case "nuevo":
                request.getRequestDispatcher("addCliente.jsp").forward(request, response);
                break;
            case "Agregar":
                String nombre = request.getParameter("txtNombre");
                String apellido = request.getParameter("txtApellido");
                String correoElectronico = request.getParameter("txtCorreoElectronico");
                String telefono = request.getParameter("txtTelefono");
                String direccion = request.getParameter("txtDireccion");
                String cedula = request.getParameter("txtCedula");

                Cliente cliente = new Cliente(nombre, apellido, correoElectronico, telefono, direccion, cedula);
                int resultado = clienteDAO.add(cliente);
                if (resultado != 0) {
                    request.setAttribute("config", "alert alert-success");
                    request.setAttribute("mensaje", "EL CLIENTE SE HA AGREGADO CON ÉXITO");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                } else {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "NO SE HA PODIDO GUARDAR EL CLIENTE");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            case "editar":
                int idCliente = Integer.valueOf(request.getParameter("id"));
                Cliente c = clienteDAO.getCliente(idCliente);
                request.setAttribute("cliente", c);
                request.getRequestDispatcher("editarCliente.jsp").forward(request, response);
                break;
            case "Actualizar":
                int idClienteToUpdate = Integer.valueOf(request.getParameter("txtId"));
                String nombreToUpdate = request.getParameter("txtNombre");
                String apellidoToUpdate = request.getParameter("txtApellido");
                String correoElectronicoToUpdate = request.getParameter("txtCorreoElectronico");
                String telefonoToUpdate = request.getParameter("txtTelefono");
                String direccionToUpdate = request.getParameter("txtDireccion");
                String cedulaToUpdate = request.getParameter("txtCedula");

                Cliente clienteToUpdate = new Cliente(idClienteToUpdate, nombreToUpdate, apellidoToUpdate, correoElectronicoToUpdate, telefonoToUpdate, direccionToUpdate, cedulaToUpdate);
                int respuesta = clienteDAO.update(clienteToUpdate);
                if (respuesta != 0) {
                    request.setAttribute("config", "alert alert-success");
                    request.setAttribute("mensaje", "EL CLIENTE SE HA ACTUALIZADO CON ÉXITO");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                } else {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "NO SE HA PODIDO ACTUALIZAR EL CLIENTE");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            case "eliminar":
                int idClienteToDelete = Integer.valueOf(request.getParameter("id"));
                Cliente clienteABorrar = clienteDAO.getCliente(idClienteToDelete);
                int res = clienteDAO.delete(idClienteToDelete);
                if (res != 0) {
                    request.setAttribute("config", "alert alert-warning");
                    request.setAttribute("mensaje", "EL CLIENTE SE HA ELIMINADO CON ÉXITO");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                } else {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR EL CLIENTE");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            default:
                throw new AssertionError();
        }
    }
}
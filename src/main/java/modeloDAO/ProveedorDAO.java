package modeloDAO;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;

public class ProveedorDAO implements InterfazProveedorDAO {

    public List<Proveedor> getProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombreProveedor(rs.getString("nombre_proveedor"));
                proveedor.setCorreoElectronico(rs.getString("correo_electronico"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setRuc(rs.getString("ruc"));  
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            Conexion.cerrarConexion();
        }

        return proveedores;
    }

    public Proveedor getProveedor(int id) {
        String sql = "SELECT * FROM proveedor WHERE id = ?;";
        Proveedor proveedor = null;
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombreProveedor(rs.getString("nombre_proveedor"));
                proveedor.setCorreoElectronico(rs.getString("correo_electronico"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setRuc(rs.getString("ruc"));  
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor por ID: " + e);
        } finally {
            Conexion.cerrarConexion();
        }

        return proveedor;
    }

    public Proveedor getId(int id) {
        return getProveedor(id); 
    }

    public int add(Proveedor proveedor) {
        int resultado = 0;
        String sql = "INSERT INTO proveedor(nombre_proveedor, correo_electronico, telefono, direccion, ruc) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setString(2, proveedor.getCorreoElectronico());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getRuc());  
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar en la base de datos: " + e);
        } finally {
            Conexion.cerrarConexion();
        }

        return resultado;
    }

    public int update(Proveedor proveedor) {
        int resultado = 0;
        String sql = "UPDATE proveedor SET nombre_proveedor = ?, correo_electronico = ?, telefono = ?, direccion = ?, ruc = ? WHERE id = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setString(2, proveedor.getCorreoElectronico());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getRuc());  
            ps.setInt(6, proveedor.getId());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar en la base de datos: " + e);
        } finally {
            Conexion.cerrarConexion();
        }

        return resultado;
    }

    public int delete(int id) {
        int resultado = 0;
        String sql = "DELETE FROM proveedor WHERE id = ?";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar en la base de datos: " + e);
        } finally {
            Conexion.cerrarConexion();
        }

        return resultado;
    }
}

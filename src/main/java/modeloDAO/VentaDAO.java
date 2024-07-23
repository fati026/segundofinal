package modeloDAO;

import modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;

public class VentaDAO implements InterfazVentaDAO {
    public List<Venta> getVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM venta;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setNumeroFactura(rs.getString("numero_factura"));
                venta.setClienteId(rs.getInt("cliente_id"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setFormaPago(rs.getString("forma_pago"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return ventas;
    }

   
    public Venta getId(int id) {
        String sql = "SELECT * FROM venta WHERE id = ?;";
        Venta venta = new Venta();
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                venta.setId(rs.getInt("id"));
                venta.setNumeroFactura(rs.getString("numero_factura"));
                venta.setClienteId(rs.getInt("cliente_id"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setFormaPago(rs.getString("forma_pago"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return venta;
    }

    public int add(Venta venta) {
        int resultado = 0;
        if (existeNumeroFactura(venta.getNumeroFactura())) {
            System.err.println("Error: El número de factura ya existe.");
            return resultado; // Retorna 0 indicando que no se ha insertado nada
        }

        String sql = "INSERT INTO venta (numero_factura, cliente_id, fecha, total, forma_pago) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, venta.getNumeroFactura());
            ps.setInt(2, venta.getClienteId());
            ps.setDate(3, venta.getFecha());
            ps.setDouble(4, venta.getTotal());
            ps.setString(5, venta.getFormaPago());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar en la base de datos: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

    public int update(Venta venta) {
        int resultado = 0;
        String sql = "UPDATE venta SET numero_factura = ?, cliente_id = ?, fecha = ?, total = ?, forma_pago = ? WHERE id = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, venta.getNumeroFactura());
            ps.setInt(2, venta.getClienteId());
            ps.setDate(3, venta.getFecha());
            ps.setDouble(4, venta.getTotal());
            ps.setString(5, venta.getFormaPago());
            ps.setInt(6, venta.getId());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar en la base de datos: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

   
    public int delete(int id) {
        int resultado = 0;
        String sql = "DELETE FROM venta WHERE id = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error durante el borrado: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

    // Método para verificar si el número de factura ya existe
    private boolean existeNumeroFactura(String numeroFactura) {
        String sql = "SELECT COUNT(*) FROM venta WHERE numero_factura = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, numeroFactura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar número de factura: " + e);
        }  
        return false;
    }
}
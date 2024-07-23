package modeloDAO;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Compra;

public class CompraDAO implements InterfazCompraDAO {
    public List<Compra> getCompras() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setNumeroFactura(rs.getString("numero_factura"));
                compra.setProveedorId(rs.getInt("proveedor_id"));
                compra.setFecha(rs.getDate("fecha"));
                compra.setTotal(rs.getDouble("total"));
                compra.setFormaPago(rs.getString("forma_pago"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return compras;
    }

    @Override
    public Compra getId(int id) {
        String sql = "SELECT * FROM compra WHERE id = ?;";
        Compra compra = new Compra();
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                compra.setId(rs.getInt("id"));
                compra.setNumeroFactura(rs.getString("numero_factura"));
                compra.setProveedorId(rs.getInt("proveedor_id"));
                compra.setFecha(rs.getDate("fecha"));
                compra.setTotal(rs.getDouble("total"));
                compra.setFormaPago(rs.getString("forma_pago"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return compra;
    }

    @Override
    public int add(Compra compra) {
        int resultado = 0;
        if (existeNumeroFactura(compra.getNumeroFactura())) {
            System.err.println("Error: El número de factura ya existe.");
            return resultado; // Retorna 0 indicando que no se ha insertado nada
        }

        String sql = "INSERT INTO compra (numero_factura, proveedor_id, fecha, total, forma_pago) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, compra.getNumeroFactura());
            ps.setInt(2, compra.getProveedorId());
            ps.setDate(3, compra.getFecha());
            ps.setDouble(4, compra.getTotal());
            ps.setString(5, compra.getFormaPago());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar en la base de datos: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

    @Override
    public int update(Compra compra) {
        int resultado = 0;
        String sql = "UPDATE compra SET numero_factura = ?, proveedor_id = ?, fecha = ?, total = ?, forma_pago = ? WHERE id = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, compra.getNumeroFactura());
            ps.setInt(2, compra.getProveedorId());
            ps.setDate(3, compra.getFecha());
            ps.setDouble(4, compra.getTotal());
            ps.setString(5, compra.getFormaPago());
            ps.setInt(6, compra.getId());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar en la base de datos: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

    @Override
    public int delete(int id) {
        int resultado = 0;
        String sql = "DELETE FROM compra WHERE id = ?;";
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
        String sql = "SELECT COUNT(*) FROM compra WHERE numero_factura = ?;";
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

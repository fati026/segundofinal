package modeloDAO;

import modelo.VentaDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;

public class VentaDetalleDAO implements InterfazVentaDetalleDAO {
    public List<VentaDetalle> getDetalles() {
        List<VentaDetalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM venta_detalle;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VentaDetalle detalle = new VentaDetalle();
                detalle.setId(rs.getInt("id"));
                detalle.setVentaId(rs.getInt("venta_id"));
                detalle.setProductoId(rs.getInt("producto_id"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setTotalArticulo(rs.getDouble("total_articulo"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return detalles;
    }

    public VentaDetalle getId(int id) {
        String sql = "SELECT * FROM venta_detalle WHERE id = ?;";
        VentaDetalle detalle = new VentaDetalle();
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                detalle.setId(rs.getInt("id"));
                detalle.setVentaId(rs.getInt("venta_id"));
                detalle.setProductoId(rs.getInt("producto_id"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setTotalArticulo(rs.getDouble("total_articulo"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return detalle;
    }

    public int add(VentaDetalle detalle) {
        int resultado = 0;
        String sql = "INSERT INTO venta_detalle (venta_id, producto_id, cantidad, precio_unitario, total_articulo) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, detalle.getVentaId());
            ps.setInt(2, detalle.getProductoId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setDouble(5, detalle.getTotalArticulo());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar en la base de datos: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

    public int update(VentaDetalle detalle) {
        int resultado = 0;
        String sql = "UPDATE venta_detalle SET venta_id = ?, producto_id = ?, cantidad = ?, precio_unitario = ?, total_articulo = ? WHERE id = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, detalle.getVentaId());
            ps.setInt(2, detalle.getProductoId());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setDouble(5, detalle.getTotalArticulo());
            ps.setInt(6, detalle.getId());
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
        String sql = "DELETE FROM venta_detalle WHERE id = ?;";
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
}
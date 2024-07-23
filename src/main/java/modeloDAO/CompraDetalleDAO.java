package modeloDAO;

import modelo.CompraDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;

public class CompraDetalleDAO implements InterfazCompraDetalleDAO {
    public List<CompraDetalle> getDetalles() {
        List<CompraDetalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM compra_detalle;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompraDetalle detalle = new CompraDetalle();
                detalle.setId(rs.getInt("id"));
                detalle.setCompraId(rs.getInt("compra_id"));
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

 
    public CompraDetalle getId(int id) {
        String sql = "SELECT * FROM compra_detalle WHERE id = ?;";
        CompraDetalle detalle = new CompraDetalle();
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                detalle.setId(rs.getInt("id"));
                detalle.setCompraId(rs.getInt("compra_id"));
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

   
    public int add(CompraDetalle detalle) {
        int resultado = 0;
        String sql = "INSERT INTO compra_detalle (compra_id, producto_id, cantidad, precio_unitario, total_articulo) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, detalle.getCompraId());
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

   
    public int update(CompraDetalle detalle) {
        int resultado = 0;
        String sql = "UPDATE compra_detalle SET compra_id = ?, producto_id = ?, cantidad = ?, precio_unitario = ?, total_articulo = ? WHERE id = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, detalle.getCompraId());
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

 
    public int delete(int id) {
        int resultado = 0;
        String sql = "DELETE FROM compra_detalle WHERE id = ?;";
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
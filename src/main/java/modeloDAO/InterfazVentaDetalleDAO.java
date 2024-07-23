package modeloDAO;

import modelo.VentaDetalle;
import java.util.List;

public interface InterfazVentaDetalleDAO {
    List<VentaDetalle> getDetalles();
    VentaDetalle getId(int id);
    int add(VentaDetalle detalle);
    int update(VentaDetalle detalle);
    int delete(int id);
}
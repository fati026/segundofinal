package modeloDAO;

import modelo.CompraDetalle;
import java.util.List;

public interface InterfazCompraDetalleDAO {
    List<CompraDetalle> getDetalles();
    CompraDetalle getId(int id);
    int add(CompraDetalle detalle);
    int update(CompraDetalle detalle);
    int delete(int id);
}
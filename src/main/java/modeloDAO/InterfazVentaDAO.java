package modeloDAO;

import modelo.Venta;
import java.util.List;

public interface InterfazVentaDAO {
    public List<Venta> getVentas();
    public Venta getId(int id);
    public int add(Venta venta);
    public int update(Venta venta);
    public int delete(int id);
}
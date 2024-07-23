package modeloDAO;

import java.util.List;
import modelo.Compra;

public interface InterfazCompraDAO {
    public List<Compra> getCompras();
    public Compra getId(int id);
    public int add(Compra compra);
    public int update(Compra compra);
    public int delete(int id);
}

package modelo;

public class CompraDetalle {
    private Integer id;
    private Integer compraId;
    private Integer productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double totalArticulo;

    // Constructor vacío
    public CompraDetalle() {
    }

    // Constructor para agregar un nuevo detalle de compra
    public CompraDetalle(Integer compraId, Integer productoId, Integer cantidad, Double precioUnitario, Double totalArticulo) {
        this.compraId = compraId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalArticulo = totalArticulo;
    }

    // Constructor con ID para actualizar o eliminar
    public CompraDetalle(Integer id, Integer compraId, Integer productoId, Integer cantidad, Double precioUnitario, Double totalArticulo) {
        this.id = id;
        this.compraId = compraId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalArticulo = totalArticulo;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompraId() {
        return compraId;
    }

    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getTotalArticulo() {
        return totalArticulo;
    }

    public void setTotalArticulo(Double totalArticulo) {
        this.totalArticulo = totalArticulo;
    }
}
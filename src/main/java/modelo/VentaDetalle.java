package modelo;

public class VentaDetalle {
    private Integer id;
    private Integer ventaId;
    private Integer productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double totalArticulo;

    // Constructor vacío
    public VentaDetalle() {
    }

    // Constructor para agregar un nuevo detalle de venta
    public VentaDetalle(Integer ventaId, Integer productoId, Integer cantidad, Double precioUnitario, Double totalArticulo) {
        this.ventaId = ventaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalArticulo = totalArticulo;
    }

    // Constructor con ID para actualizar o eliminar
    public VentaDetalle(Integer id, Integer ventaId, Integer productoId, Integer cantidad, Double precioUnitario, Double totalArticulo) {
        this.id = id;
        this.ventaId = ventaId;
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

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
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
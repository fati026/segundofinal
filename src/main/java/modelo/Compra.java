package modelo;

import java.sql.Date;

public class Compra {
    private Integer id;
    private String numeroFactura;
    private Integer proveedorId;
    private Date fecha;
    private Double total;
    private String formaPago;
    private Integer idUsuario;
    private String nombreUsuario;
    private String accion;

    // Constructor vacío
    public Compra() {
    }

    // Constructor para agregar una nueva compra
    public Compra(String numeroFactura, Integer proveedorId, Date fecha, Double total, String formaPago) {
        this.numeroFactura = numeroFactura;
        this.proveedorId = proveedorId;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
    }
    
    // Constructor con ID para actualizar o eliminar
    public Compra(Integer id, String numeroFactura, Integer proveedorId, Date fecha, Double total, String formaPago) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.proveedorId = proveedorId;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
    }

    // Constructor para registrar acciones
    public Compra(String numeroFactura, Integer proveedorId, Date fecha, Double total, String formaPago, Integer idUsuario, String nombreUsuario, String accion) {
        this.numeroFactura = numeroFactura;
        this.proveedorId = proveedorId;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.accion = accion;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}

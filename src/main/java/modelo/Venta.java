package modelo;

import java.sql.Date;

public class Venta {
    private int id;
    private String numeroFactura;
    private int clienteId;
    private Date fecha;
    private double total;
    private String formaPago;

    public Venta() {
    }

    public Venta(String numeroFactura, int clienteId, Date fecha, double total, String formaPago) {
        this.numeroFactura = numeroFactura;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
    }

    public Venta(int id, String numeroFactura, int clienteId, Date fecha, double total, String formaPago) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
}
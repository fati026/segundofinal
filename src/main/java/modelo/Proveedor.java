package modelo;

public class Proveedor {
    
    private Integer id;
    private String nombreProveedor;
    private String correoElectronico;
    private String telefono;
    private String direccion;
    private String ruc;  

    public Proveedor(String nombreProveedor, String correoElectronico, String telefono, String direccion, String ruc) {
        this.nombreProveedor = nombreProveedor;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ruc = ruc;
    }
    
    public Proveedor(Integer id, String nombreProveedor, String correoElectronico, String telefono, String direccion, String ruc) {
        this.id = id;
        this.nombreProveedor = nombreProveedor;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ruc = ruc;
    }

    public Proveedor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {  
        return ruc;
    }

    public void setRuc(String ruc) {  
        this.ruc = ruc;
    }
}

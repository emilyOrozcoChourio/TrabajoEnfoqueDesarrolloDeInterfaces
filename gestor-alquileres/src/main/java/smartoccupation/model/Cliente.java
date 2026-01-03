package smartoccupation.model;

public class Cliente {
    private Integer id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String direccion;
    private String datosFacturacion;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getDatosFacturacion() { return datosFacturacion; }
    public void setDatosFacturacion(String datosFacturacion) { this.datosFacturacion = datosFacturacion; }
}

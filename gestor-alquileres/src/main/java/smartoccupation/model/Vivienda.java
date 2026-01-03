package smartoccupation.model;

public class Vivienda {
    private Integer id;
    private String codigoReferencia;
    private String ubicacion;
    private Integer metros;
    private Integer habitaciones;
    private Integer banos;
    private Double precioMensual;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCodigoReferencia() { return codigoReferencia; }
    public void setCodigoReferencia(String codigoReferencia) { this.codigoReferencia = codigoReferencia; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public Integer getMetros() { return metros; }
    public void setMetros(Integer metros) { this.metros = metros; }
    public Integer getHabitaciones() { return habitaciones; }
    public void setHabitaciones(Integer habitaciones) { this.habitaciones = habitaciones; }
    public Integer getBanos() { return banos; }
    public void setBanos(Integer banos) { this.banos = banos; }
    public Double getPrecioMensual() { return precioMensual; }
    public void setPrecioMensual(Double precioMensual) { this.precioMensual = precioMensual; }
}

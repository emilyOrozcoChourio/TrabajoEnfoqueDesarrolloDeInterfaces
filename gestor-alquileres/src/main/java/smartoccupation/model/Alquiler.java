package smartoccupation.model;

public class Alquiler {
    private Integer id;
    private String numeroExpediente;
    private String fechaEntradaIso; // formato yyyy-MM-dd
    private Integer tiempoEstimadoMeses;
    private Integer clienteId;
    private Integer viviendaId;
    private String estadoCobro;

    // Relaciones opcionales
    private Cliente cliente;
    private Vivienda vivienda;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNumeroExpediente() { return numeroExpediente; }
    public void setNumeroExpediente(String numeroExpediente) { this.numeroExpediente = numeroExpediente; }
    public String getFechaEntradaIso() { return fechaEntradaIso; }
    public void setFechaEntradaIso(String fechaEntradaIso) { this.fechaEntradaIso = fechaEntradaIso; }
    public Integer getTiempoEstimadoMeses() { return tiempoEstimadoMeses; }
    public void setTiempoEstimadoMeses(Integer tiempoEstimadoMeses) { this.tiempoEstimadoMeses = tiempoEstimadoMeses; }
    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public Integer getViviendaId() { return viviendaId; }
    public void setViviendaId(Integer viviendaId) { this.viviendaId = viviendaId; }
    public String getEstadoCobro() { return estadoCobro; }
    public void setEstadoCobro(String estadoCobro) { this.estadoCobro = estadoCobro; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Vivienda getVivienda() { return vivienda; }
    public void setVivienda(Vivienda vivienda) { this.vivienda = vivienda; }
}

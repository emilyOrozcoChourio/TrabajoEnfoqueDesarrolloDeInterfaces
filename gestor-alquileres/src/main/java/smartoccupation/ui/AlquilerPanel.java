package smartoccupation.ui;

import com.toedter.calendar.JDateChooser;
import smartoccupation.dao.AlquilerDAO;
import smartoccupation.model.Alquiler;
import smartoccupation.util.DateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class AlquilerPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JDateChooser fechaInicio = new JDateChooser();
    private final JDateChooser fechaFin = new JDateChooser();
    private final JButton btnBuscar = new JButton("Consultar alquileres");
    private final JButton btnInforme = new JButton("Generar informe CSV");

    private final JTable tabla = new JTable();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Expediente", "Entrada", "Meses", "Cliente", "DNI",
                         "Ubicación", "Ref", "Cobro", "Precio"}, 0);

    private final AlquilerDAO alquilerDAO = new AlquilerDAO();

    public AlquilerPanel() {
        setLayout(new BorderLayout(10, 10));

        add(buildTopBar(), BorderLayout.NORTH);

        tabla.setModel(modelo);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        attachListeners();
    }

    private JPanel buildTopBar() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        top.add(new JLabel("Fecha inicio:"));
        fechaInicio.setDate(new Date());
        top.add(fechaInicio);

        top.add(new JLabel("Fecha fin:"));
        fechaFin.setDate(new Date());
        top.add(fechaFin);

        top.add(btnBuscar);
        top.add(btnInforme);

        return top;
    }

    private void attachListeners() {
        btnBuscar.addActionListener(e -> consultar());
        btnInforme.addActionListener(e -> generarInforme());
    }

    private void consultar() {
        String desde = DateUtil.toIso(fechaInicio.getDate());
        String hasta = DateUtil.toIso(fechaFin.getDate());

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(this, "Selecciona ambas fechas.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fechaInicio.getDate().after(fechaFin.getDate())) {
            JOptionPane.showMessageDialog(this,
                    "La fecha de inicio no puede ser posterior a la fecha fin.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<Alquiler> resultados =
                    alquilerDAO.findByFechaEntradaBetween(desde, hasta);
            fillTable(resultados);
            JOptionPane.showMessageDialog(this,
                    "Consulta realizada. Registros: " + resultados.size(),
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en la consulta: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillTable(List<Alquiler> alquileres) {
        modelo.setRowCount(0);
        for (Alquiler a : alquileres) {
            modelo.addRow(new Object[]{
                    a.getNumeroExpediente(),
                    a.getFechaEntradaIso(),
                    a.getTiempoEstimadoMeses(),
                    a.getCliente() != null ? a.getCliente().getNombre() : "",
                    a.getCliente() != null ? a.getCliente().getDni() : "",
                    a.getVivienda() != null ? a.getVivienda().getUbicacion() : "",
                    a.getVivienda() != null ? a.getVivienda().getCodigoReferencia() : "",
                    a.getEstadoCobro(),
                    a.getVivienda() != null ? a.getVivienda().getPrecioMensual() : 0.0
            });
        }
    }

    private void generarInforme() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar informe CSV");
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (java.io.FileWriter writer =
                             new java.io.FileWriter(chooser.getSelectedFile())) {
                    // Encabezados
                    for (int j = 0; j < modelo.getColumnCount(); j++) {
                        writer.write(modelo.getColumnName(j));
                        if (j < modelo.getColumnCount() - 1) writer.write(";");
                    }
                    writer.write("\n");
                    // Filas
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        for (int j = 0; j < modelo.getColumnCount(); j++) {
                            Object val = modelo.getValueAt(i, j);
                            writer.write(val == null ? "" : val.toString());
                            if (j < modelo.getColumnCount() - 1) writer.write(";");
                        }
                        writer.write("\n");
                    }
                }
                JOptionPane.showMessageDialog(this,
                        "Informe generado correctamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al generar informe: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


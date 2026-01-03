package smartoccupation.dao;

import smartoccupation.model.Vivienda;
import java.sql.*;
import java.util.*;

public class ViviendaDAO {

    public Optional<Vivienda> findById(int id) {
        String sql = "SELECT * FROM viviendas WHERE id = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Vivienda> findAll() {
        List<Vivienda> viviendas = new ArrayList<>();
        String sql = "SELECT * FROM viviendas";
        try (Connection conn = DB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                viviendas.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viviendas;
    }

    private Vivienda map(ResultSet rs) throws SQLException {
        Vivienda v = new Vivienda();
        v.setId(rs.getInt("id"));
        v.setCodigoReferencia(rs.getString("codigo_referencia"));
        v.setUbicacion(rs.getString("ubicacion"));
        v.setMetros(rs.getInt("metros"));
        v.setHabitaciones(rs.getInt("habitaciones"));
        v.setBanos(rs.getInt("banos"));
        v.setPrecioMensual(rs.getDouble("precio_mensual"));
        return v;
    }
}

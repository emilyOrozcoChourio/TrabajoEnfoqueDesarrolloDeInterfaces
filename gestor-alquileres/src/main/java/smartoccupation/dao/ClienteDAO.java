package smartoccupation.dao;

import smartoccupation.model.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDAO {

    public Optional<Cliente> findById(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
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

    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    private Cliente map(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        c.setDni(rs.getString("dni"));
        c.setEmail(rs.getString("email"));
        c.setTelefono(rs.getString("telefono"));
        c.setDireccion(rs.getString("direccion"));
        c.setDatosFacturacion(rs.getString("datos_facturacion"));
        return c;
    }
}

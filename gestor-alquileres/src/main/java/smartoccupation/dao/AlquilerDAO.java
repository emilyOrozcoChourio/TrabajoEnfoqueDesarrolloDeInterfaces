package smartoccupation.dao;

import smartoccupation.model.*;
import java.sql.*;
import java.util.*;

public class AlquilerDAO {

    public List<Alquiler> findByFechaEntradaBetween(String desdeIso, String hastaIso) {
    	String sql =
    		    "SELECT " +
    		    "  a.id AS alquiler_id, a.numero_expediente, a.fecha_entrada, a.tiempo_estimado_meses, a.estado_cobro, " +
    		    "  a.cliente_id, a.vivienda_id, " +
    		    "  c.id AS cliente_id, c.nombre, c.dni, c.email, c.telefono, c.direccion, c.datos_facturacion, " +
    		    "  v.id AS vivienda_id, v.codigo_referencia, v.ubicacion, v.metros, v.habitaciones, v.banos, v.precio_mensual " +
    		    "FROM alquileres a " +
    		    "JOIN clientes c ON c.id = a.cliente_id " +
    		    "JOIN viviendas v ON v.id = a.vivienda_id " +
    		    "WHERE a.fecha_entrada >= ? AND a.fecha_entrada <= ? " +
    		    "ORDER BY a.fecha_entrada ASC";


        List<Alquiler> list = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, desdeIso);
            ps.setString(2, hastaIso);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapJoin(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Alquiler mapJoin(ResultSet rs) throws SQLException {
        Alquiler a = new Alquiler();
        a.setId(rs.getInt("alquiler_id"));
        a.setNumeroExpediente(rs.getString("numero_expediente"));
        a.setFechaEntradaIso(rs.getString("fecha_entrada"));
        a.setTiempoEstimadoMeses(rs.getInt("tiempo_estimado_meses"));
        a.setClienteId(rs.getInt("cliente_id"));
        a.setViviendaId(rs.getInt("vivienda_id"));
        a.setEstadoCobro(rs.getString("estado_cobro"));

        Cliente c = new Cliente();
        c.setId(rs.getInt("cliente_id"));
        c.setNombre(rs.getString("nombre"));
        c.setDni(rs.getString("dni"));
        c.setEmail(rs.getString("email"));
        c.setTelefono(rs.getString("telefono"));
        c.setDireccion(rs.getString("direccion"));
        c.setDatosFacturacion(rs.getString("datos_facturacion"));

        Vivienda v = new Vivienda();
        v.setId(rs.getInt("vivienda_id"));
        v.setCodigoReferencia(rs.getString("codigo_referencia"));
        v.setUbicacion(rs.getString("ubicacion"));
        v.setMetros(rs.getInt("metros"));
        v.setHabitaciones(rs.getInt("habitaciones"));
        v.setBanos(rs.getInt("banos"));
        v.setPrecioMensual(rs.getDouble("precio_mensual"));

        a.setCliente(c);
        a.setVivienda(v);
        return a;
    }
}

package smartoccupation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static final String URL =
		    "jdbc:mysql://localhost:3306/smartoccupation?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";           // tu usuario de MySQL
    private static final String PASSWORD = "emily.15";   // tu contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

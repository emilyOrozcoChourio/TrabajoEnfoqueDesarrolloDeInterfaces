package smartoccupation.dao;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DB.getConnection()) {
            System.out.println("Conexión OK: " + conn.getMetaData().getURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

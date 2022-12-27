package crud.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private ConnectionFactory() {}

        public static Connection getConnection() {
            try {
                DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "1205");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }
    }


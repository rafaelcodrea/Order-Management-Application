package dataAccess.connectionFactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

        private static final Logger LOGGER = Logger.getLogger(Connector.class.getName());
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String DBURL = "jdbc:mysql://localhost:3306/hotelpersistence";
        private static final String USER = "root";
        private static final String PASS = "password";

        private static Connector singleInstance = new Connector();

        private Connector() {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private Connection createConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DBURL, USER, PASS);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to connect to the database");
                e.printStackTrace();
            }
            return connection;
        }

        public static Connection getConnection() {
            return singleInstance.createConnection();
        }

        public static void close(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection");
                }
            }
        }

        public static void close(Statement statement) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
                }
            }
        }

        public static void close(ResultSet resultSet) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occurred while trying to close the ResultSet");
                }
            }
        }
}

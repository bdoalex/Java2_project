package isen.project.model.daos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {

    /**
     * Connection to the DB
     */
    private DataSourceFactory() {

        throw new IllegalStateException("This is a static class that should not be instantiated");
    }

    public static Connection getConnection() throws SQLException {
        Connection cnx = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        return cnx;
    }

}

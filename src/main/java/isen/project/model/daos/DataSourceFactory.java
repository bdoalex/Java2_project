package isen.project.model.daos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {

    private DataSourceFactory() {

        throw new IllegalStateException("This is a static class that should not be instantiated");
    }

    public static Connection getConnection() throws SQLException {
        Connection cnx = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        // Magically, java static analysis can ensure you that cnx is never null, so no need to check
        DatabaseMetaData meta = cnx.getMetaData();
        return cnx;
    }

}

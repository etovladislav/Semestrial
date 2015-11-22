package ru.kpfu.itis.repository.connect;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vladislav on 18.10.2015.
 */
public class DataBaseConnect {
    private static Connection con;
    private static DataBaseConnect instance;
    private static DataSource dataSource;

    private DataBaseConnect() {
    }

    public Connection getConnection()
    {
        return con;
    }

    public static synchronized DataBaseConnect getInstance() {
        if (instance == null) {
            try {
                instance = new DataBaseConnect();
                Context ctx = new InitialContext();
                instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentsDS");
                con = dataSource.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}

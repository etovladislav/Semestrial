package ru.kpfu.itis.repository;

import ru.kpfu.itis.repository.connect.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthUserRepository {
    public boolean authUser(String login, String password) {
        String returnedUsername = null;
        String returnedPassword = null;
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT login,password FROM users WHERE login = ? AND password = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while (rs.next()) {
                returnedUsername = rs.getString("login");
                returnedPassword = rs.getString("password");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if(returnedUsername != null) {
            return returnedPassword.equals(password);
        }
        return false;
    }
}

package ru.kpfu.itis.repository;

import ru.kpfu.itis.repository.connect.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUserRepository {
    public boolean checkUserExists(String username) {
        String returnedUsername = null;
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT login FROM users WHERE login = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                returnedUsername = rs.getString("login");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnedUsername == null;
    }
}

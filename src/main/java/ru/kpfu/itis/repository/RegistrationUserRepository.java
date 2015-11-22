package ru.kpfu.itis.repository;

import org.apache.commons.lang3.StringEscapeUtils;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.connect.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationUserRepository {
    public void registrationUser(User user) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO users "
                    + "(login, firstname, lastname, email,password)"
                    + "VALUES( ?, ?, ?, ?, ?)");
            stmt.setString(1, StringEscapeUtils.escapeHtml4(user.getLogin()));
            stmt.setString(2, StringEscapeUtils.escapeHtml4(user.getFirstName()));
            stmt.setString(3, StringEscapeUtils.escapeHtml4(user.getLastName()));
            stmt.setString(4, StringEscapeUtils.escapeHtml4(user.getEmail()));
            stmt.setString(5, StringEscapeUtils.escapeHtml4(user.getPassword()));
            stmt.execute();
            connection.commit();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}

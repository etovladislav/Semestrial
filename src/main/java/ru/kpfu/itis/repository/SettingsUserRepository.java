package ru.kpfu.itis.repository;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import ru.kpfu.itis.repository.connect.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by vladislav on 09.11.15.
 */
public class SettingsUserRepository {
    private static final Logger log = Logger.getLogger(SettingsUserRepository.class);

    public void updateInfoUser(String firstname, String lastname, String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            firstname = StringEscapeUtils.escapeHtml4(firstname);
            lastname = StringEscapeUtils.escapeHtml4(lastname);
                    connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("UPDATE users " +
                    "SET firstname = ?, lastname = ? WHERE login = ?");
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, username);
            stmt.execute();
            connection.commit();
            stmt.close();
            log.info("User: "+username+" updated information on himself "+firstname+" "+lastname);
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

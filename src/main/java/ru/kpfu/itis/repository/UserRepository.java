package ru.kpfu.itis.repository;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.connect.DataBaseConnect;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final Logger log = Logger.getLogger(UserRepository.class);

    public List getPostUser(User user) {

        return null;
    }

    public User findUserByLogin(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt;
        ResultSet rs;
        User user = new User();
        try {
            stmt = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setLogin(rs.getString("login"));
                user.setAboutUser(rs.getString("aboutuser"));
                user.setEmail(rs.getString("email"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getAvatar(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt;
        String photo = null;
        ResultSet rs;
        try {
            stmt = connection.prepareStatement("SELECT avatar FROM user_avatar WHERE username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                photo = rs.getString("avatar");
            }
            rs.close();
            stmt.close();
            return photo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveAvatar(String username, String avatar) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO user_avatar "
                    + "(avatar, username) "
                    + "VALUES( ?, ?)");
            stmt.setString(1, avatar);
            stmt.setString(2, username);
            stmt.execute();
            connection.commit();
            stmt.close();
            log.info("User: " + username + " saved avatar");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


    public String updateAvatar(String username, String avatar) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        String oldAvatar = getAvatar(username);
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("UPDATE user_avatar SET avatar = ? WHERE username = ?");
            stmt.setString(1, avatar);
            stmt.setString(2, username);
            stmt.execute();
            connection.commit();
            stmt.close();
            log.info("User: " + username + " updated avatar");
            return oldAvatar;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return oldAvatar;
    }

    public List<User> getAllUsers() {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM users ORDER BY login DESC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setLogin(rs.getString("login"));
                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Integer getCountPosts(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer res = null;
        try {
            stmt = connection.prepareStatement("SELECT count(*) as val FROM post WHERE username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                res = rs.getInt("val");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<User> searchUsers(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM users WHERE login LIKE ? ORDER BY login DESC");
            stmt.setString(1, username + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setLogin(rs.getString("login"));
                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Boolean isFollow(String username, String usernameFollow) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT username FROM follow WHERE username = ? AND username_follow = ? ");
            stmt.setString(1, username);
            stmt.setString(2, usernameFollow);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("username") != null;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void follow(String username, String usernameFollow) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO follow "
                    + "(username, username_follow) "
                    + "VALUES( ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, usernameFollow);
            stmt.execute();
            connection.commit();
            stmt.close();
            log.info("User: "+username+" followed on "+usernameFollow);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void unFollow(String username, String usernameFollow) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("DELETE FROM follow WHERE username = ? AND " +
                    "username_follow = ?");
            stmt.setString(1, username);
            stmt.setString(2, usernameFollow);
            stmt.execute();
            connection.commit();
            stmt.close();
            log.info("User: "+username+" unsubscribed from "+usernameFollow);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<User> getAllFollower(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM users WHERE login in (SELECT username FROM follow " +
                    "WHERE username_follow = ? )");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setLogin(rs.getString("login"));
                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getUserFollow(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM users WHERE login in (SELECT username_follow FROM follow " +
                    "WHERE username = ? )");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setLogin(rs.getString("login"));
                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}

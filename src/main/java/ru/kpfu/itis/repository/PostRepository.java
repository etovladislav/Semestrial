package ru.kpfu.itis.repository;

import org.apache.commons.lang3.StringEscapeUtils;
import ru.kpfu.itis.model.Comment;
import ru.kpfu.itis.model.Post;
import ru.kpfu.itis.repository.connect.DataBaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladislav on 08.11.15.
 */
public class PostRepository {
    private UserRepository userRepository = new UserRepository();

    public List<Post> getAllPosts(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<Post>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM post WHERE username = ? ORDER BY id DESC");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setDate(rs.getDate("date"));
                post.setSendUser(userRepository.findUserByLogin(rs.getString("username_send")));
                post.setUser(userRepository.findUserByLogin(rs.getString("username")));
                post.setText(rs.getString("text"));
                post.setId(rs.getLong("id"));
                post.setLike(getCountLike(post.getId()));
                posts.add(post);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private Long getCountLike(Long id) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT COUNT(*) as kol FROM like_a_post WHERE id_post = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getLong("kol");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public void addPost(String username, String text, String usernameSend) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        text = StringEscapeUtils.escapeHtml4(text);
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO post "
                    + "(text,username,username_send,date)"
                    + "VALUES( ?, ?, ?, ?)");
            stmt.setString(1, text);
            stmt.setString(2, username);
            stmt.setString(3, usernameSend);
            stmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
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

    public void deletePost(String id) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("DELETE FROM post WHERE id = ?");
            stmt.setLong(1, Long.valueOf(id));
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

    public List<Post> getNewsPosts(String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<Post>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM post p WHERE p.username_send in (SELECT username_follow" +
                    " FROM follow WHERE username = ?) AND p.username = p.username_send ORDER BY id DESC");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setDate(rs.getDate("date"));
                post.setSendUser(userRepository.findUserByLogin(rs.getString("username_send")));
                post.setUser(userRepository.findUserByLogin(rs.getString("username")));
                post.setText(rs.getString("text"));
                post.setId(rs.getLong("id"));
                posts.add(post);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public void likePost(Long idPost, String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO like_a_post "
                    + "(id_post,username)"
                    + "VALUES(?, ?)");
            stmt.setLong(1, idPost);
            stmt.setString(2, username);
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

    public void unLikePost(Long idPost, String username) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("DELETE FROM like_a_post WHERE id_post = ? AND username = ?");
            stmt.setLong(1, idPost);
            stmt.setString(2, username);
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

    public void addComment(Long idPost, String username, String text) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO post_comments "
                    + "(post_id, user_sender, text)"
                    + "VALUES(?, ?, ?)");
            stmt.setLong(1, idPost);
            stmt.setString(2, username);
            stmt.setString(3, text);
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

    public List<Comment> getComments(Long id) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Comment> posts = new ArrayList<Comment>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM post_comments WHERE post_id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setUser(userRepository.findUserByLogin(rs.getString("user_sender")));
                comment.setText(rs.getString("text"));
                posts.add(comment);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public Post getPost(Long id) {
        Connection connection = DataBaseConnect.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM post WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setDate(rs.getDate("date"));
                post.setSendUser(userRepository.findUserByLogin(rs.getString("username_send")));
                post.setUser(userRepository.findUserByLogin(rs.getString("username")));
                post.setText(rs.getString("text"));
                post.setId(rs.getLong("id"));
                post.setLike(getCountLike(post.getId()));
                return post;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

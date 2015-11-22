package ru.kpfu.itis.model;

/**
 * Created by vladislav on 16.11.15.
 */
public class Comment {

    private User user;

    private String text;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

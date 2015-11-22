package ru.kpfu.itis.model;

import java.util.Date;

public class Post {

    private Long id;

    private String text;

    private Date date;

    private User sendUser;

    private User user;

    private Long like;

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }


    public Post() {
    }

    public Post(String text, Date date, User user) {
        this.text = text;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }//��������?

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

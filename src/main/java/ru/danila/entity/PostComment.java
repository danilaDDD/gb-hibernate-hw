package ru.danila.entity;

import jakarta.persistence.*;

/*
 * CREATE TABLE post_comment
 * (
 *     id      BIGINT PRIMARY KEY,
 *     text    TEXT NOT NULL,
 *     post_id BIGINT NOT NULL ,
 *     user_id BIGINT NOT NULL ,
 *     FOREIGN KEY (user_id) REFERENCES user (id),
 *     FOREIGN KEY (post_id) REFERENCES post(id)
 * )
 */

@Entity
@Table(name = "post_comment")
public class PostComment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostComment(long id, Post post, String text, User user) {
        this.id = id;
        this.post = post;
        this.text = text;
        this.user = user;
    }

    public PostComment() {

    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("PostComment{%s, %s, %s, %s}", id, post, text, user);
    }
}

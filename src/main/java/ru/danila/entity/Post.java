package ru.danila.entity;

import jakarta.persistence.*;

import java.util.List;

/*
CREATE TABLE post(
    id BIGINT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    text TEXT NULL);
 */

@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostComment> postComments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Post() {
    }

    public Post(long id, String title, String text, User user) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    @Override
    public String toString() {
        return String.format("Post{%s, %s}", id, title);
    }
}

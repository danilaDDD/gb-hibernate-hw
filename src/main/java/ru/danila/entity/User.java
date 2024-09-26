package ru.danila.entity;

import jakarta.persistence.*;

import java.util.List;

/*
CREATE TABLE user(id BIGINT PRIMARY KEY, name VARCHAR(50) NOT NULL);
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostComment> postComments;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    @Override
    public String toString() {
        return String.format("User{%s, %s}", id, name);
    }


    public List<Post> getPosts(){
        return posts;
    }
}
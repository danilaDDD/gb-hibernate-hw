package ru.danila.entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class BaseEntity {

    @Column(name = "created", nullable = true)
    private LocalDateTime created;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void onCreate(){
        created = LocalDateTime.now();
    }
}

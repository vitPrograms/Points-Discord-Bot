package com.example.points_bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VoiceActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date joinDate;
    private Date leftDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public VoiceActivity(){}

    public VoiceActivity(Date joinDate, User user) {
        this.joinDate = joinDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLeftDate() {
        return leftDate;
    }

    public void setLeftDate(Date leftDate) {
        this.leftDate = leftDate;
    }
}

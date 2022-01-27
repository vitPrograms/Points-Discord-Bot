package com.example.points_bot.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long discordId;
    private Long points;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VoiceActivity> activity;

    public User(){}

    public User(Long discordId, Long points) {
        this.discordId = discordId;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public Long getDiscordId() {
        return discordId;
    }

    public void setDiscordId(Long discordId) {
        this.discordId = discordId;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public List<VoiceActivity> getActivity() {
        return activity;
    }

    public void setActivity(List<VoiceActivity> activity) {
        this.activity = activity;
    }
}

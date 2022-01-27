package com.example.points_bot.repository;

import com.example.points_bot.entity.User;
import com.example.points_bot.entity.VoiceActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceActivityRepository extends CrudRepository<VoiceActivity, Long> {
    public VoiceActivity findByUser(User user);
}

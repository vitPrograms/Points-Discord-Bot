package com.example.points_bot.repository;

import com.example.points_bot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByDiscordId(Long discordId);
}

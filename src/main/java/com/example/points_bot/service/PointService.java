package com.example.points_bot.service;

import com.example.points_bot.entity.User;
import com.example.points_bot.exception.UserAlreadyExistException;
import com.example.points_bot.exception.UserNotFoundException;
import com.example.points_bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    public Long getUserPoints(Long discordId) throws UserAlreadyExistException {
        User user = userRepo.findByDiscordId(discordId);
        if(user == null) {
            User newUser = userService.addUser(new User(discordId, 0l));
            return newUser.getPoints();
        }

        return user.getPoints();
    }

    public Long addPoints(Long discordId, Long points) throws UserNotFoundException {
        User user = userRepo.findByDiscordId(discordId);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setPoints(user.getPoints() + points);
        userRepo.save(user);

        return user.getDiscordId();
    }
}

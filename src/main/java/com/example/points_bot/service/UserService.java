package com.example.points_bot.service;

import com.example.points_bot.entity.User;
import com.example.points_bot.exception.UserAlreadyExistException;
import com.example.points_bot.exception.UserNotFoundException;
import com.example.points_bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByDiscordId(Long discordId) {
        User user = userRepo.findByDiscordId(discordId);

        if(user == null) {
            User newUser = new User(discordId, 0l);
            userRepo.save(newUser);
            return newUser;
        }
        return user;
    }

    public User addUser(User user) throws UserAlreadyExistException {
        User userCheck = userRepo.findByDiscordId(user.getDiscordId());
        if(userCheck != null) {
            throw new UserAlreadyExistException("User with same discord_id is already exist");
        }

        return userRepo.save(user);
    }
}

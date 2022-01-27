package com.example.points_bot.service;

import com.example.points_bot.entity.User;
import com.example.points_bot.entity.VoiceActivity;
import com.example.points_bot.exception.UserHasNotActivityException;
import com.example.points_bot.exception.UserNotFoundException;
import com.example.points_bot.exception.VoiceActivityNotBeFoundException;
import com.example.points_bot.repository.UserRepository;
import com.example.points_bot.repository.VoiceActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class VoiceService {

    @Autowired
    private VoiceActivityRepository voiceRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    public VoiceActivity addVoiceActivity(Long discordId, VoiceActivity voiceActivity) throws UserNotFoundException {
        User user = userRepo.findByDiscordId(discordId);

        if(user == null) {
            throw new UserNotFoundException("Bad discordId value");
        }

        if(user.getActivity() != null) {
            user.setActivity(null);
            userRepo.save(user);
        }

        voiceActivity.setUser(user);
        return voiceRepo.save(voiceActivity);
    }

    public Long calculateActivity(Long discordId, Long timeStamp) throws UserNotFoundException, UserHasNotActivityException {
        Date leftDate = new Date(timeStamp);
        User user = userRepo.findByDiscordId(discordId);

        if(user == null) {
            throw new UserNotFoundException("Bad discordId value");
        }

        if(user.getActivity().equals(null)) {
            throw new UserHasNotActivityException("User activity is null");
        }

        for(VoiceActivity voiceActivity : user.getActivity()) {
            if(voiceActivity.getLeftDate() == null) {
                Long diffTime = leftDate.getTime() - voiceActivity.getJoinDate().getTime();
                voiceActivity.setLeftDate(leftDate);
                voiceRepo.save(voiceActivity);
                user.setPoints(user.getPoints() + Math.round(diffTime/1000/10));
                break;
            }
        }
        userRepo.save(user);
        return user.getPoints();
    }

    public Iterable<VoiceActivity> getAllActivities() {
        return voiceRepo.findAll();
    }
}

package com.example.points_bot.controller;

import com.example.points_bot.entity.VoiceActivity;
import com.example.points_bot.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/voice")
public class VoiceController {

    @Autowired
    VoiceService voiceService;

    @PostMapping("")
    public ResponseEntity addVoiceActivity(@RequestParam Long discordId, @RequestBody VoiceActivity voiceActivity) {
        try {
            return ResponseEntity.ok().body(voiceService.addVoiceActivity(discordId, voiceActivity));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/left")
    public ResponseEntity calculateVoiceActivity(@RequestParam Long discordId,
                                                 @RequestParam Long timeStamp) {
        try {
            return ResponseEntity.ok().body(voiceService.calculateActivity(discordId, timeStamp));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity getAllActivities() {
        try {
            return ResponseEntity.ok().body(voiceService.getAllActivities());
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.example.points_bot.controller;

import com.example.points_bot.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/points")
public class PointController {

    @Autowired
    PointService pointService;

    @GetMapping("/{discordId}")
    public ResponseEntity getUserPoints(@PathVariable Long discordId) {
        try{
            return ResponseEntity.ok().body(pointService.getUserPoints(discordId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{discordId}")
    public ResponseEntity addUserPoints(@PathVariable Long discordId, @RequestParam Long points) {
        try{
            return ResponseEntity.ok().body(pointService.addPoints(discordId, points));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

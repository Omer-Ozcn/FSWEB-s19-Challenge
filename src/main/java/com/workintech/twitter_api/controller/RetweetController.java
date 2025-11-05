package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.retweet.RetweetRequest;
import com.workintech.twitter_api.entity.Retweet;
import com.workintech.twitter_api.service.RetweetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/retweet")
public class RetweetController {
    private final RetweetService retweetService;

    @PostMapping
    public ResponseEntity<Retweet> create(@Valid @RequestBody RetweetRequest req) {
        return ResponseEntity.ok(retweetService.retweet(req.tweetId(), req.userId()));
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<Void> delete(@PathVariable Long tweetId, @RequestParam Long userId) {
        retweetService.delete(tweetId, userId);
        return ResponseEntity.noContent().build();
    }
}

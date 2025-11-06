package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.retweet.RetweetRequest;
import com.workintech.twitter_api.dto.retweet.RetweetResponse;
import com.workintech.twitter_api.entity.Retweet;
import com.workintech.twitter_api.mapper.RetweetMapper;
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
    private final RetweetMapper retweetMapper;

    @PostMapping
    public ResponseEntity<RetweetResponse> create(@Valid @RequestBody RetweetRequest req) {
        var saved = retweetService.retweet(req.tweetId(), req.userId());
        return ResponseEntity.ok(retweetMapper.toResponse(saved));
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<Void> delete(@PathVariable Long tweetId, @RequestParam Long userId) {
        retweetService.delete(tweetId, userId);
        return ResponseEntity.noContent().build();
    }
}

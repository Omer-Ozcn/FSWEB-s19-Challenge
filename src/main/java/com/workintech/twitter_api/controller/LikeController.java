package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.service.LikeService;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody LikeReq req) {
        likeService.like(req.getTweetId(), req.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestBody LikeReq req) {
        likeService.dislike(req.getTweetId(), req.getUserId());
        return ResponseEntity.ok().build();
    }

    @Data
    public static class LikeReq {
        @NotNull private Long tweetId;
        @NotNull private Long userId;
    }
}

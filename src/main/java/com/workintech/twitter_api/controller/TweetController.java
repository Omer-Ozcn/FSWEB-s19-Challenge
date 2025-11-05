package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.dto.tweet.TweetCreateRequest;
import com.workintech.twitter_api.dto.tweet.TweetResponse;
import com.workintech.twitter_api.dto.tweet.TweetUpdateRequest;
import com.workintech.twitter_api.entity.Tweet;
import com.workintech.twitter_api.mapper.TweetMapper;
import com.workintech.twitter_api.service.TweetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweet")
public class TweetController {
    private final TweetService tweetService;
    private final TweetMapper tweetMapper;

    @PostMapping
    public ResponseEntity<TweetResponse> create(@Valid @RequestBody TweetCreateRequest req) {
        Tweet created = tweetService.create(req.userId(), req.content());
        return ResponseEntity
                .created(URI.create("/tweet/" + created.getId()))
                .body(tweetMapper.toResponse(created));
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<List<TweetResponse>> findByUserId(@RequestParam Long userId) {
        List<Tweet> tweets = tweetService.getByUserId(userId);
        return ResponseEntity.ok(tweetMapper.toResponseList(tweets));
    }

    @GetMapping("/findById")
    public ResponseEntity<TweetResponse> findById(@RequestParam Long tweetId) {
        Tweet t = tweetService.getById(tweetId);
        return ResponseEntity.ok(tweetMapper.toResponse(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TweetResponse> update(@PathVariable Long id, @Valid @RequestBody TweetUpdateRequest req) {
        Tweet updated = tweetService.updateContent(id, req.requesterId(), req.content());
        return ResponseEntity.ok(tweetMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long requesterId) {
        tweetService.delete(id, requesterId);
        return ResponseEntity.noContent().build();
    }
}

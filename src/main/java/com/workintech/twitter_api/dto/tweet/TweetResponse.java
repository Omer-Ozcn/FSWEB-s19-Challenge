package com.workintech.twitter_api.dto.tweet;

import java.time.Instant;

public record TweetResponse(
        Long id,
        Long userId,
        String username,
        String content,
        Instant createdAt,
        Long likeCount
) {}

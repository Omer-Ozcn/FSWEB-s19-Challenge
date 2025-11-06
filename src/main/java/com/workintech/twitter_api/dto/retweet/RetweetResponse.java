package com.workintech.twitter_api.dto.retweet;

import java.time.Instant;

public record RetweetResponse(
        Long id,
        Long tweetId,
        Long userId,
        Instant createdAt
) {}
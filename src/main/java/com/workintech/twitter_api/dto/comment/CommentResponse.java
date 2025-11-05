package com.workintech.twitter_api.dto.comment;

import java.time.Instant;


public record CommentResponse(
        Long id,
        Long tweetId,
        Long userId,
        String content,
        Instant createdAt,
        Instant updatedAt
) {}

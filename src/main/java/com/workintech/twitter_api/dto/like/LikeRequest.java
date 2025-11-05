package com.workintech.twitter_api.dto.like;

import jakarta.validation.constraints.NotNull;

public record LikeRequest(
        @NotNull Long tweetId,
        @NotNull Long userId
) {}

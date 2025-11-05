package com.workintech.twitter_api.dto.retweet;

import jakarta.validation.constraints.NotNull;

public record RetweetRequest(
        @NotNull Long tweetId,
        @NotNull Long userId
) {}


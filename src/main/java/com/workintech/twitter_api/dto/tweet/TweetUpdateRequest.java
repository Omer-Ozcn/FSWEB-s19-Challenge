package com.workintech.twitter_api.dto.tweet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TweetUpdateRequest(
        @NotNull Long requesterId,
        @NotBlank @Size(max = 280) String content
) {}

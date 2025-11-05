package com.workintech.twitter_api.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentUpdateRequest(
        @NotNull Long requesterId,
        @NotBlank @Size(max = 280) String content
) {}

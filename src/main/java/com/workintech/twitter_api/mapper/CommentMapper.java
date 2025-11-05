package com.workintech.twitter_api.mapper;

import com.workintech.twitter_api.dto.comment.CommentResponse;
import com.workintech.twitter_api.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment c) {
        if (c == null) return null;
        return new CommentResponse(
                c.getId(),
                c.getTweet().getId(),
                c.getUser().getId(),
                c.getContent(),
                c.getCreatedAt(),
                c.getUpdatedAt()
        );
    }

    public List<CommentResponse> toList(List<Comment> list) {
        return list.stream().map(this::toResponse).toList();
    }
}
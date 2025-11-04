package com.workintech.twitter_api.service;

import com.workintech.twitter_api.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment create(Long tweetId, Long userId, String content);
    Comment update(Long commentId, Long requesterId, String content);
    void delete(Long commentId, Long requesterId);
    List<Comment> listByTweet(Long tweetId);
}

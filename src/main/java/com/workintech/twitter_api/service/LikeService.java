package com.workintech.twitter_api.service;

public interface LikeService {
    void like(Long tweetId, Long userId);
    void dislike(Long tweetId, Long userId);
    long countByTweet(Long tweetId);
}
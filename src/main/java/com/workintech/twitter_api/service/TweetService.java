package com.workintech.twitter_api.service;

import com.workintech.twitter_api.entity.Tweet;

import java.util.List;

public interface TweetService {
    Tweet create(Long userId, String content);
    Tweet getById(Long id);
    List<Tweet> getByUserId(Long userId);
    Tweet updateContent(Long tweetId, Long requesterId, String content);
    void delete(Long tweetId, Long requesterId);
}

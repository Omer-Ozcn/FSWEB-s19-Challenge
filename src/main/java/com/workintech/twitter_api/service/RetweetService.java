package com.workintech.twitter_api.service;

import com.workintech.twitter_api.entity.Retweet;

import java.util.List;

public interface RetweetService {
    Retweet retweet(Long tweetId, Long userId);
    void delete(Long tweetId, Long userId);
    long countByTweet(Long tweetId);
    List<Retweet> listByUser(Long userId);
}

package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entity.TweetLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetLikeRepository extends JpaRepository<TweetLike, Long> {
    boolean existsByTweetIdAndUserId(Long tweetId, Long userId);
    long countByTweetId(Long tweetId);
    void deleteByTweetIdAndUserId(Long tweetId, Long userId);
}

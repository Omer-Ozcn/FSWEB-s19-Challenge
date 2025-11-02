package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    boolean existsByOriginalTweetIdAndUserId(Long tweetId, Long userId);
    void deleteByOriginalTweetIdAndUserId(Long tweetId, Long userId);
    long countByOriginalTweetId(Long tweetId);
    List<Retweet> findByUserIdOrderByCreatedAtDesc(Long userId);
}

package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUserIdOrderByCreatedAtDesc(Long userId);
    Optional<Tweet> findByIdAndUserId(Long id, Long userId);
    long countByUserId(Long userId);
}

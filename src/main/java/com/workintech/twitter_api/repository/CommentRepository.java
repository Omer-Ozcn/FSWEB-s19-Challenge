package com.workintech.twitter_api.repository;

import com.workintech.twitter_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTweetIdOrderByCreatedAtAsc(Long tweetId);
    Optional<Comment> findByIdAndUserId(Long id, Long userId);
    long countByTweetId(Long tweetId);
}

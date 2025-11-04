package com.workintech.twitter_api.service;

import com.workintech.twitter_api.entity.Comment;
import com.workintech.twitter_api.entity.Tweet;
import com.workintech.twitter_api.entity.User;
import com.workintech.twitter_api.exceptions.ForbiddenException;
import com.workintech.twitter_api.exceptions.NotFoundException;
import com.workintech.twitter_api.repository.CommentRepository;
import com.workintech.twitter_api.repository.TweetRepository;
import com.workintech.twitter_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepo;
    private final TweetRepository tweetRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public Comment create(Long tweetId, Long userId, String content) {
        Tweet tweet = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new NotFoundException("Tweet not found: " + tweetId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));

        Comment c = new Comment();
        c.setTweet(tweet);
        c.setUser(user);
        c.setContent(content);
        c.setCreatedAt(Instant.now());
        return commentRepo.save(c);
    }

    @Override
    @Transactional
    public Comment update(Long commentId, Long requesterId, String content) {
        Comment c = commentRepo.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found: " + commentId));
        if (!c.getUser().getId().equals(requesterId)) {
            throw new ForbiddenException("Only comment owner can update");
        }
        c.setContent(content);
        c.setUpdatedAt(Instant.now());
        return commentRepo.save(c);
    }

    @Override
    @Transactional
    public void delete(Long commentId, Long requesterId) {
        Comment c = commentRepo.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found: " + commentId));
        Long tweetOwnerId = c.getTweet().getUser().getId();
        Long commentOwnerId = c.getUser().getId();

        if (!requesterId.equals(tweetOwnerId) && !requesterId.equals(commentOwnerId)) {
            throw new ForbiddenException("Only tweet owner or comment owner can delete");
        }
        commentRepo.delete(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> listByTweet(Long tweetId) {
        return commentRepo.findByTweetIdOrderByCreatedAtAsc(tweetId);
    }
}

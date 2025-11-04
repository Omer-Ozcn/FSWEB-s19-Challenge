package com.workintech.twitter_api.service;

import com.workintech.twitter_api.entity.Tweet;
import com.workintech.twitter_api.entity.User;
import com.workintech.twitter_api.exceptions.ForbiddenException;
import com.workintech.twitter_api.exceptions.NotFoundException;
import com.workintech.twitter_api.repository.TweetRepository;
import com.workintech.twitter_api.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public Tweet create(Long userId, String content) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));

        Tweet t = new Tweet();
        t.setUser(user);
        t.setContent(content);
        t.setCreatedAt(Instant.now());
        return tweetRepo.save(t);
    }

    @Override
    @Transactional(readOnly = true)
    public Tweet getById(Long id) {
        return tweetRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Tweet not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tweet> getByUserId(Long userId) {
        return tweetRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    @Transactional
    public Tweet updateContent(Long tweetId, Long requesterId, String content) {
        Tweet t = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new NotFoundException("Tweet not found: " + tweetId));

        if (!t.getUser().getId().equals(requesterId)) {
            throw new ForbiddenException("Only owner can update the tweet");
        }
        t.setContent(content);
        t.setUpdatedAt(Instant.now());
        return tweetRepo.save(t);
    }

    @Override
    @Transactional
    public void delete(Long tweetId, Long requesterId) {
        Tweet t = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new NotFoundException("Tweet not found: " + tweetId));
        if (!t.getUser().getId().equals(requesterId)) {
            throw new ForbiddenException("Only owner can delete the tweet");
        }
        tweetRepo.delete(t);
    }
}

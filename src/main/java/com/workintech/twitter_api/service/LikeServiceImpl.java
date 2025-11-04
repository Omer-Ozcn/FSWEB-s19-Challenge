package com.workintech.twitter_api.service;

import com.workintech.twitter_api.entity.Tweet;
import com.workintech.twitter_api.entity.TweetLike;
import com.workintech.twitter_api.entity.User;
import com.workintech.twitter_api.exceptions.NotFoundException;
import com.workintech.twitter_api.repository.TweetLikeRepository;
import com.workintech.twitter_api.repository.TweetRepository;
import com.workintech.twitter_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final TweetLikeRepository likeRepo;
    private final TweetRepository tweetRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public void like(Long tweetId, Long userId) {
        if (likeRepo.existsByTweetIdAndUserId(tweetId, userId)) {
            return; // zaten like'lanmış, no-op
        }
        Tweet tweet = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new NotFoundException("Tweet not found: " + tweetId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));

        TweetLike l = new TweetLike();
        l.setTweet(tweet);
        l.setUser(user);
        l.setCreatedAt(Instant.now());
        likeRepo.save(l);
    }

    @Override
    @Transactional
    public void dislike(Long tweetId, Long userId) {
        likeRepo.deleteByTweetIdAndUserId(tweetId, userId); // yoksa no-op
    }

    @Override
    @Transactional(readOnly = true)
    public long countByTweet(Long tweetId) {
        return likeRepo.countByTweetId(tweetId);
    }
}

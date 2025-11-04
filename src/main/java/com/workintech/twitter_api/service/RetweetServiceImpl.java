package com.workintech.twitter_api.service;

import com.workintech.twitter_api.entity.Retweet;
import com.workintech.twitter_api.entity.Tweet;
import com.workintech.twitter_api.entity.User;
import com.workintech.twitter_api.exceptions.NotFoundException;
import com.workintech.twitter_api.repository.RetweetRepository;
import com.workintech.twitter_api.repository.TweetRepository;
import com.workintech.twitter_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepo;
    private final TweetRepository tweetRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public Retweet retweet(Long tweetId, Long userId) {
        if (retweetRepo.existsByOriginalTweetIdAndUserId(tweetId, userId)) {
            List<Retweet> list = retweetRepo.findByUserIdOrderByCreatedAtDesc(userId);
            return list.stream().filter(r -> r.getOriginalTweet().getId().equals(tweetId)).findFirst().orElse(null);
        }
        Tweet tweet = tweetRepo.findById(tweetId)
                .orElseThrow(() -> new NotFoundException("Tweet not found: " + tweetId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));

        Retweet r = new Retweet();
        r.setOriginalTweet(tweet);
        r.setUser(user);
        r.setCreatedAt(Instant.now());
        return retweetRepo.save(r);
    }

    @Override
    @Transactional
    public void delete(Long tweetId, Long userId) {
        retweetRepo.deleteByOriginalTweetIdAndUserId(tweetId, userId); // yoksa no-op
    }

    @Override
    @Transactional(readOnly = true)
    public long countByTweet(Long tweetId) {
        return retweetRepo.countByOriginalTweetId(tweetId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Retweet> listByUser(Long userId) {
        return retweetRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }
}

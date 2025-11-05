package com.workintech.twitter_api.mapper;

import com.workintech.twitter_api.dto.tweet.TweetResponse;
import com.workintech.twitter_api.entity.Tweet;
import com.workintech.twitter_api.repository.TweetLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TweetMapper {

    private final TweetLikeRepository likeRepo;

    public TweetResponse toResponse(Tweet t) {
        if (t == null) return null;
        long likeCount = likeRepo.countByTweetId(t.getId());
        return new TweetResponse(
                t.getId(),
                t.getUser().getId(),
                t.getUser().getUsername(),
                t.getContent(),
                t.getCreatedAt(),
                likeCount
        );
    }

    public List<TweetResponse> toResponseList(List<Tweet> tweets) {
        return tweets.stream().map(this::toResponse).toList();
    }
}

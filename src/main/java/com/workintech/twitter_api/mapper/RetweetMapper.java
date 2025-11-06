package com.workintech.twitter_api.mapper;

import com.workintech.twitter_api.dto.retweet.RetweetResponse;
import com.workintech.twitter_api.entity.Retweet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetweetMapper {
    public RetweetResponse toResponse(Retweet r) {
        if (r == null) return null;
        return new RetweetResponse(
                r.getId(),
                r.getOriginalTweet().getId(),
                r.getUser().getId(),
                r.getCreatedAt()
        );
    }
    public List<RetweetResponse> toList(List<Retweet> list) {
        return list.stream().map(this::toResponse).toList();
    }
}
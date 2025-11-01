package com.workintech.twitter_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tweet_like",
        uniqueConstraints = @UniqueConstraint(
                name = "tweet_like_unique_tweet_user",
                columnNames = {"tweet_id", "user_id"}
        ))
@Getter @Setter @NoArgsConstructor
public class TweetLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tweet_id", nullable = false,
            foreignKey = @ForeignKey(name = "tweet_like_tweet_id_fkey"))
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "tweet_like_user_id_fkey"))
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}

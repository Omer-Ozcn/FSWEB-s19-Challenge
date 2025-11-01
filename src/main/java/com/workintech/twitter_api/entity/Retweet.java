package com.workintech.twitter_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "retweet",
        uniqueConstraints = @UniqueConstraint(
                name = "retweet_unique_tweet_user",
                columnNames = {"original_tweet_id", "user_id"}
        ))
@Getter @Setter @NoArgsConstructor
public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "original_tweet_id", nullable = false,
            foreignKey = @ForeignKey(name = "retweet_original_tweet_id_fkey"))
    private Tweet originalTweet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "retweet_user_id_fkey"))
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}

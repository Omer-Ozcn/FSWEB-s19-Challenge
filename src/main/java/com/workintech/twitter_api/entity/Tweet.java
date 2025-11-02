package com.workintech.twitter_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tweet")
@Getter
@Setter
@NoArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "tweet_user_id_fkey"))
    private User user;

    @NotBlank @Size(max = 280)
    @Column(nullable = false, length = 280)
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "tweet", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "tweet", fetch = FetchType.LAZY)
    private List<TweetLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "originalTweet", fetch = FetchType.LAZY)
    private List<Retweet> retweets = new ArrayList<>();
}


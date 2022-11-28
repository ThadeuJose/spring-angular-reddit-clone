package com.example.springredditclone.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;

    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;

    @Nullable
    private String url;

    @Nullable
    @Lob // Large Object Data
    private String description;

    private Integer voteCount = 0;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;

    public Post() {
    }

    public Post(Long postId, String postName, String url, String description, Integer voteCount, User user,
            Instant createdDate, Subreddit subreddit) {
        this.postId = postId;
        this.postName = postName;
        this.url = url;
        this.description = description;
        this.voteCount = voteCount;
        this.user = user;
        this.createdDate = createdDate;
        this.subreddit = subreddit;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }

}

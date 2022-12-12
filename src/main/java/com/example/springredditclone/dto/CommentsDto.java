package com.example.springredditclone.dto;

import java.time.Instant;

public class CommentsDto {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;

    public CommentsDto() {
    }

    public CommentsDto(Long id, Long postId, Instant createdDate, String text, String userName) {
        this.id = id;
        this.postId = postId;
        this.createdDate = createdDate;
        this.text = text;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public String getText() {
        return text;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

package com.example.springredditclone.dto;

import com.example.springredditclone.model.VoteType;

public class VoteDto {
    private VoteType voteType;
    private Long postId;

    public VoteDto() {
    }

    public VoteDto(VoteType voteType, Long postId) {
        this.voteType = voteType;
        this.postId = postId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}

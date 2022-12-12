package com.example.springredditclone.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;

    private VoteType voteType;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    public Vote() {
    }

    public Vote(VoteType voteType, @NotNull Post post, User user) {
        this.voteType = voteType;
        this.post = post;
        this.user = user;
    }

    public VoteType getVoteType() {
        return voteType;
    }

}
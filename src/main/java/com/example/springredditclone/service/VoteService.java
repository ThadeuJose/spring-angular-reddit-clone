package com.example.springredditclone.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springredditclone.dto.VoteDto;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Vote;
import com.example.springredditclone.model.VoteType;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    public VoteService(VoteRepository voteRepository, PostRepository postRepository, AuthService authService) {
        this.voteRepository = voteRepository;
        this.postRepository = postRepository;
        this.authService = authService;
    }

    @Transactional
    public void save(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return new Vote(voteDto.getVoteType(), post, authService.getCurrentUser());
    }

}

package com.example.springredditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springredditclone.dto.CommentsDto;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.mapper.CommentMapper;
import com.example.springredditclone.model.NotificationEmail;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.CommentRepository;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.UserRepository;

@Service
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public CommentService(PostRepository postRepository, UserRepository userRepository, AuthService authService,
            CommentMapper commentMapper, CommentRepository commentRepository, MailContentBuilder mailContentBuilder,
            MailService mailService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.mailContentBuilder = mailContentBuilder;
        this.mailService = mailService;
    }

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow((() -> new PostNotFoundException(commentsDto.getPostId().toString())));
        commentRepository.save(commentMapper.map(commentsDto, post, authService.getCurrentUser()));

        String message = mailContentBuilder
                .build(post.getUser().getUsername() + "posted a comment in your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendEmail(
                new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post).stream().map(commentMapper::map).collect(Collectors.toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user).stream().map(commentMapper::map).collect(Collectors.toList());
    }
}

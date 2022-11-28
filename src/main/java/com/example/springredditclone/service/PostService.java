package com.example.springredditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.SubredditNotFoundException;
import com.example.springredditclone.mapper.PostMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.SubredditRepository;
import com.example.springredditclone.repository.UserRepository;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, UserRepository userRepository,
            SubredditRepository subredditRepository, AuthService authService, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.subredditRepository = subredditRepository;
        this.authService = authService;
        this.postMapper = postMapper;
    }

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentuser = authService.getCurrentUser();
        postRepository.save(postMapper.map(postRequest, subreddit, currentuser));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    public List<PostResponse> getPostBySubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException(id.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    public List<PostResponse> getPostByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user).stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

}

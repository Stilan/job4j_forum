package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {

    private static final AtomicInteger POST_ID = new AtomicInteger();

    private final Map<Integer, Post> posts = new HashMap<>();


    public Collection<Post> getAll() {
        return posts.values();
    }

    public Post findByTypeId(int id) {
        return posts.get(id);
    }

    public void save(Post post) {

        if (post.getId() == 0) {
          post.setId(POST_ID.incrementAndGet());
          post.setCreated(Calendar.getInstance());
          posts.put(post.getId(), post);
        }
        posts.replace(post.getId(), post);
    }
}
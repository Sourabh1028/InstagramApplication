package com.geekster.InstagramApplication.Service;

import com.geekster.InstagramApplication.Model.Post;
import com.geekster.InstagramApplication.Repositary.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    public void addPost(Post post) {
        postRepo.save(post);
    }
}

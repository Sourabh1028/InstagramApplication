package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {

}

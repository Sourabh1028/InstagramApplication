package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<PostLike, Long> {
}

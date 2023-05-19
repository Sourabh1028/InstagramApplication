package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.InstagramFollowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepo extends JpaRepository<InstagramFollowing, Long> {

}

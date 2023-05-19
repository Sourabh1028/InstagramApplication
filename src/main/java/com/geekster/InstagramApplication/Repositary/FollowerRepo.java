package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.InstagramFollower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepo extends JpaRepository<InstagramFollower, Long> {

}

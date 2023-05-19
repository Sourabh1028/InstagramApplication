package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.InstagramComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<InstagramComments, Long> {
}

package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
}

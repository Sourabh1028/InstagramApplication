package com.geekster.InstagramApplication.Controller;

import com.geekster.InstagramApplication.Service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("follower")
public class FollowerController {

    @Autowired
    FollowerService followerService;
}

package com.devjin.springstu.domain.controller;

import com.devjin.springstu.domain.dto.response.ResUser;
import com.devjin.springstu.domain.dto.request.ReqPostUser;
import com.devjin.springstu.domain.entity.User;
import com.devjin.springstu.domain.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService _userService)
    {
        this.userService = _userService;
    }

    @GetMapping
    public List<ResUser> getUser()
    {
        return userService.getUser();
    }

   @PostMapping
    public User initUser(@RequestBody @Valid ReqPostUser _reqPostUser)
   {
       return userService.initUser(_reqPostUser);
   }

}

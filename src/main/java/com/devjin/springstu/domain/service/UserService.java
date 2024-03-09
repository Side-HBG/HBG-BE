package com.devjin.springstu.domain.service;

import com.devjin.springstu.domain.dto.request.ReqPostUser;
import com.devjin.springstu.domain.dto.response.ResUser;
import com.devjin.springstu.domain.entity.User;
import com.devjin.springstu.domain.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository _userRepository)
    {
        this.userRepository = _userRepository;
    }
    @Transactional(readOnly = true)
    public List<ResUser> getUser()
    {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,User.COL_ID)).stream()
                .map(ResUser::new).collect(Collectors.toList());
    }
    @Transactional
    public User initUser(ReqPostUser _reqPostUser)
    {
        userRepository.save(_reqPostUser.toEntity());
        return _reqPostUser.toEntity();
    }

}

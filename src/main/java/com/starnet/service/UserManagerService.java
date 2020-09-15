package com.starnet.service;

import com.starnet.po.User;
import com.starnet.vo.UserQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserManagerService
{
    User getUser(Long id);//查询一个

    Page<User> listUser(Pageable pageable, UserQuery user);//分页查询

    User saveUser(User user);//新增

    User updateUser(Long id,User user);//修改

    void deleteUser(Long id);
}

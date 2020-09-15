package com.starnet.service;

import com.starnet.po.User;

public interface UserService {

    User checkUser(String username,String password);
}

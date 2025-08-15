package org.csu.hoaserver.service;

import DO.User;
import VO.LoginVO;

public interface UserService {
    LoginVO login(String username, String password);

    String register(User user);

}

package org.csu.hoaserver.service;

import DO.User;
import DTO.UserLoginDTO;
import VO.LoginVO;

public interface UserService {
    LoginVO login(String username, String password);

    String register(User user);

    User wxLogin(UserLoginDTO userLoginDTO);
}

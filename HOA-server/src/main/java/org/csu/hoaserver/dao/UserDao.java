package org.csu.hoaserver.dao;

import DO.Song;
import DO.User;
import enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.csu.hoaserver.annotation.AutoFill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao {
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
    @AutoFill(OperationType.INSERT)
    void insertUser(User user);
    void updateLastLoginTime(String username);

    User getByOauthUid(String openid);
}

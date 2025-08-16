package org.csu.hoaserver.dao;

import DO.Song;
import DO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao {
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
    void insertUser(User user);
    void updateLastLoginTime(String username);

}

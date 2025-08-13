package org.csu.hoaserver.dao;

import DO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao {
    User findUserByUsername(String username);
}

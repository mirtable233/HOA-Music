package org.csu.hoaserver.dao;


import DO.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyDao {
    void save(Reply reply);
    List<Reply> list(Integer commentId);
}

package org.csu.hoaserver.dao;

import DO.Comment;
import DTO.CommentSaveDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {

    void save(Comment comment);

    List<Comment> list(Integer type, Integer targetId);
}

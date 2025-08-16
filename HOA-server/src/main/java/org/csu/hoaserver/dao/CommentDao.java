package org.csu.hoaserver.dao;

import DO.Comment;
import DTO.CommentSaveDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao {

    void save(Comment comment);
}

package org.csu.hoaserver.dao;

import DO.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SongDao {
    void uploadSong(@Param("song")Song song);
    void uploadGenresBySong(@Param("id") Integer id,
                            @Param("genreIds") List<Integer> genreIds);
    // 多个参数要用param注解绑定
}

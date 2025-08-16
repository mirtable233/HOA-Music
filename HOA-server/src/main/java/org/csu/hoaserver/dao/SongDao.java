package org.csu.hoaserver.dao;

import DO.Song;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SongDao {
    void uploadSong(List<Song> songs);
}

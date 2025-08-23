package org.csu.hoaserver.dao;

import DO.Playlist;
import DTO.PlaylistSaveDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlaylistDao {
    public void save(PlaylistSaveDTO playlist);
}

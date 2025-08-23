package org.csu.hoaserver.service;

import DTO.SongUploadDTO;
import com.aliyuncs.exceptions.ClientException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;

public interface SongService {
    void uploadSong(SongUploadDTO songUploadDTO) throws IOException, ClientException,
            CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException;
}

package org.csu.hoaserver.service;

import DTO.BatchUploadDTO;
import com.aliyuncs.exceptions.ClientException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;
import java.util.List;

public interface SongService {
    void uploadSong(BatchUploadDTO batchUploadDTO) throws IOException, ClientException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException;
}

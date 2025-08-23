package org.csu.hoaserver.controller;

import DTO.SongUploadDTO;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.service.SongService;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.CommonResponse;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/upload")
    public CommonResponse<String> batchUploadSongs(SongUploadDTO songUploadDTO) throws IOException,
            ClientException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        songService.uploadSong(songUploadDTO);
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/test")
    public String test() {
        return "test";
    }
}

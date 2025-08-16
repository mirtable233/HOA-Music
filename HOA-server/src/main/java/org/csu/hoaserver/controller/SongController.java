package org.csu.hoaserver.controller;

import DTO.BatchUploadDTO;
import com.aliyuncs.exceptions.ClientException;
import context.UserContext;
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
import org.springframework.web.multipart.MultipartFile;
import response.CommonResponse;
import util.AliyunOSSOperator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/upload")
    public CommonResponse<String> batchUploadSongs(BatchUploadDTO batchUploadDTO) throws IOException,
            ClientException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        songService.uploadSong(batchUploadDTO);
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/test")
    public String test() {
        return "test";
    }
}

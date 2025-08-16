package org.csu.hoaserver.service.impl;

import DO.Song;
import DTO.BatchUploadDTO;
import com.aliyun.oss.OSSException;
import com.aliyuncs.exceptions.ClientException;
import context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.dao.SongDao;
import org.csu.hoaserver.dao.UserDao;
import org.csu.hoaserver.service.SongService;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import util.AliyunOSSOperator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SongServiceImpl implements SongService {

    @Autowired
    AliyunOSSOperator ossOperator;
    @Autowired
    SongDao songDao;

    @Override
    @Transactional
    public void uploadSong(BatchUploadDTO batchUploadDTO) throws IOException,
            ClientException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        Integer uploaderId = UserContext.getCurrentId();
        List<Song> songs = new ArrayList<>();
        log.info("批量上传歌曲文件，上传者id：[{}]", uploaderId);

            MultipartFile songFile = batchUploadDTO.getSongFile();
            String url = ossOperator.uploadFile(songFile);
            File tempFile = File.createTempFile("temp", ".mp3");
            songFile.transferTo(tempFile);
            AudioFile audioFile = AudioFileIO.read(tempFile);
            Integer duration = audioFile.getAudioHeader().getTrackLength();
            String cover = ossOperator.uploadFile(batchUploadDTO.getCoverFile());
            String lyric = ossOperator.uploadFile(batchUploadDTO.getLyricFile());
            Song song = new Song();
            BeanUtils.copyProperties(batchUploadDTO, song);
            song.setCover(cover);
            song.setLyric(lyric);
            song.setDuration(duration);
            song.setUrl(url);
            song.setUploaderId(uploaderId);
            song.setUploadDate(LocalDate.now());
            songs.add(song);

        songDao.uploadSong(songs);
    }
}

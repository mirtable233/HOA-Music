package org.csu.hoaserver.service.impl;

import DO.Song;
import DTO.SongUploadDTO;
import com.aliyuncs.exceptions.ClientException;
import context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.dao.SongDao;
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

@Service
@Slf4j
public class SongServiceImpl implements SongService {

    @Autowired
    AliyunOSSOperator ossOperator;
    @Autowired
    SongDao songDao;

    @Override
    @Transactional
    public void uploadSong(SongUploadDTO songUploadDTO) throws IOException,
            ClientException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        Integer uploaderId = UserContext.getCurrentId();
        log.info("批量上传歌曲文件，上传者id：[{}]", uploaderId);

            MultipartFile songFile = songUploadDTO.getSongFile();
            String url = ossOperator.uploadFile(songFile);
            File tempFile = File.createTempFile("temp", ".mp3");
            songFile.transferTo(tempFile);
            AudioFile audioFile = AudioFileIO.read(tempFile);
            Integer duration = audioFile.getAudioHeader().getTrackLength();
            String cover = ossOperator.uploadFile(songUploadDTO.getCoverFile());
            Song song = new Song();
            BeanUtils.copyProperties(songUploadDTO, song);
            if(songUploadDTO.getLyricFile()!=null) {
                String lyric = ossOperator.uploadFile(songUploadDTO.getLyricFile());
                song.setLyric(lyric);
            }
            song.setCover(cover);
            song.setDuration(duration);
            song.setUrl(url);
            song.setUploaderId(uploaderId);
            song.setUploadDate(LocalDate.now());
            songDao.uploadSong(song);
            songDao.uploadGenresBySong(song.getId(),songUploadDTO.getGenreIds());

    }
}

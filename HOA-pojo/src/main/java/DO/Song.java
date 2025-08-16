package DO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private Integer id;          // 歌曲ID
    private String name;         // 歌曲名称
    private Integer duration;    // 时长（秒）
    private String mainSingerId;   // 主唱
    private String producer;     // 制作人
    private String composer;     // 作曲
    private String label;        // 唱片公司/发行方
    private String language;     // 语言
    private String description;  // 简介/描述
    private String cover;        // 封面图片地址
    private String url;          // 歌曲文件地址
    private String lyric;        // 歌词文本或地址
    private Integer uploaderId;  // 上传者ID（关联user表）
    private LocalDate releaseDate;    // 发行日期
    private LocalDate uploadDate;     // 上传日期
}

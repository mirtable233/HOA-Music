package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongUploadDTO {
    private MultipartFile songFile;
    private MultipartFile coverFile;
    private MultipartFile lyricFile;
    private String name;
    private String mainSingerId;
    private String producer;
    private String composer;
    private String label;
    private String language;
    private String description;
    private LocalDate releaseDate;
    private List<Integer> genreIds;
}

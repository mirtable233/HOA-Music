package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchUploadDTO {
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
}

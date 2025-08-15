package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class batchUploadDTO {
    private String name;
    private String mainSingerId;
    private String producer;
    private String composer;
    private String label;
    //...
}

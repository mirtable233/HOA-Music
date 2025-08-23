package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSaveDTO implements Serializable {
    private String name;
    private String cover;
    private Integer type;
    private Integer genreId;
    private String description;
    private LocalDate releaseDate;
    private LocalDate uploadDate;
    private List<Integer> songIds;
}

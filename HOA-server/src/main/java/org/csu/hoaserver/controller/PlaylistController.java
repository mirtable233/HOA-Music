package org.csu.hoaserver.controller;

import DO.Playlist;
import DTO.PlaylistSaveDTO;
import org.csu.hoaserver.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.CommonResponse;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/save")
    public CommonResponse<String> save(PlaylistSaveDTO playlistSaveDTO) {
        playlistService.save(playlistSaveDTO);
        return CommonResponse.createForSuccess();
    }

}

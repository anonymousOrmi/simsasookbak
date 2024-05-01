package com.simsasookbak.external.youtube.controller;

import com.simsasookbak.external.youtube.service.YoutubeService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/youtube")
public class YoutubeController {
    private final YoutubeService youtubeService;
    @GetMapping
    public ResponseEntity<?> searchVideos() throws IOException {
        youtubeService.searchVideos();
        return ResponseEntity.ok().build();
    }
}

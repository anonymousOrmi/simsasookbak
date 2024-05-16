package com.simsasookbak.external.youtube.controller;

import com.simsasookbak.external.youtube.service.YoutubeService;
import com.simsasookbak.external.youtube.dto.response.YouTubeResponse;
import java.io.IOException;
import java.util.List;
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
    public ResponseEntity<List<YouTubeResponse>> getVideoIds() throws IOException {
        List<YouTubeResponse> responses = youtubeService.getVideos();
        return ResponseEntity.ok(responses);
    }
}

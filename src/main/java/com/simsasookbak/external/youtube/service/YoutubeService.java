package com.simsasookbak.external.youtube.service;

import static com.simsasookbak.global.exception.ErrorMessage.YOUTUBE_VIDEO_NOT_FOUND;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.simsasookbak.external.youtube.domain.Youtube;
import com.simsasookbak.external.youtube.dto.YoutubeProperties;
import com.simsasookbak.external.youtube.repository.YoutubeRepository;
import com.simsasookbak.external.youtube.dto.response.YouTubeResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class YoutubeService {
    private final YoutubeRepository youtubeRepository;

    @Value("${youtube.api.key}")
    private String API_KEY;

    public List<YouTubeResponse> getVideos() throws IOException {
        if (ObjectUtils.isEmpty(findAllExpiredYoutube())) {
            searchYoutube();
        }
        return findAllYoutube().stream()
                .map(x -> new YouTubeResponse(x.getVideoId()))
                .toList();
    }

    private void searchYoutube() throws IOException {
        YouTube youtube  = new YouTube.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                request -> {})
                .build();

        Search.List search = youtube.search()
                .list(Collections.singletonList("id,snippet"));

        search.setKey(API_KEY);
        search.setPart(Collections.singletonList(YoutubeProperties.PART));
        search.setRegionCode(YoutubeProperties.REGION_CODE);
        search.setType(Collections.singletonList(YoutubeProperties.TYPE));
        search.setMaxResults(YoutubeProperties.MAX_RESULT);
        search.setQ(YoutubeProperties.QUERY);

        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResults = searchResponse.getItems();

        if (ObjectUtils.isEmpty(searchResults)) {
            throw new IllegalArgumentException(YOUTUBE_VIDEO_NOT_FOUND.getMessage());
        }

        List<Youtube> youtubes = searchResults.stream()
                .map(item -> Youtube.builder().videoId(item.getId().getVideoId()).build())
                .toList();

        if (ObjectUtils.isEmpty(findAllYoutube())) {
            saveAllYoutube(youtubes);
        } else {
            youtubes.forEach(entity -> entity.update(entity.getVideoId()));
        }
    }

    public List<Youtube> findAllExpiredYoutube() {
        LocalDateTime now = LocalDateTime.now();
        return findAllYoutube().stream()
                .filter(x -> x.getUpdatedAt().isBefore(now))
                .toList();
    }

    public List<Youtube> findAllYoutube() {
        return youtubeRepository.findAll();
    }

    public void saveAllYoutube(List<Youtube> youtubes) {
        youtubeRepository.saveAll(youtubes);
    }

}

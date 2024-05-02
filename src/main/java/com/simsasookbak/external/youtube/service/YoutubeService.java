package com.simsasookbak.external.youtube.service;

import static com.simsasookbak.external.youtube.dto.YoutubeProperties.MAX_RESULT;
import static com.simsasookbak.external.youtube.dto.YoutubeProperties.PART;
import static com.simsasookbak.external.youtube.dto.YoutubeProperties.QUERY;
import static com.simsasookbak.external.youtube.dto.YoutubeProperties.REGION_CODE;
import static com.simsasookbak.external.youtube.dto.YoutubeProperties.TYPE;
import static com.simsasookbak.global.exception.ErrorMessage.NOT_EXIST_ITEM;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.simsasookbak.external.youtube.dto.response.YouTubeResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class YoutubeService {
    @Value("${youtube.api.key}")
    private String API_KEY;

    public List<YouTubeResponse> searchVideos() throws IOException {
        List<YouTubeResponse> responses = new ArrayList<>();
        YouTube youtube  = new YouTube.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                request -> {})
                .build();

        Search.List search = youtube.search()
                .list(Collections.singletonList("id,snippet"));

        search.setKey(API_KEY);
        search.setPart(Collections.singletonList(PART));
        search.setRegionCode(REGION_CODE);
        search.setType(Collections.singletonList(TYPE));
        search.setMaxResults(MAX_RESULT);
        search.setQ(QUERY);

        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResults = searchResponse.getItems();

        if (ObjectUtils.isEmpty(searchResults)) {
            throw new IllegalArgumentException(NOT_EXIST_ITEM.getMessage());
        }

        return searchResults.stream()
                .map(x -> x.getId().getVideoId())
                .map(YouTubeResponse::new)
                .toList();
    }
}

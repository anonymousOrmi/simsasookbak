package com.simsasookbak.reservation.service;

import com.simsasookbak.reservation.dto.request.PopularRegionRequest;
import com.simsasookbak.reservation.dto.response.ReservationResponse;
import com.simsasookbak.reservation.repository.ReservationRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public List<ReservationResponse> findPopularRegionsByDate(PopularRegionRequest request) {
        Pageable pageable = PageRequest.of(0, request.getLimit());
        LocalDateTime diffDatetime = LocalDateTime.now().minusDays(request.getDay());
        return reservationRepository.findPopularRegionsByCreatedAt(diffDatetime, pageable)
                .stream()
                .map(view -> ReservationResponse.builder()
                        .region(view.getRegion())
                        .build()
                )
                .toList();
    }
}

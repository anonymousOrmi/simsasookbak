package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Transactional
    public List<AccommodationDto> searchAccommodation(String keyword) {

        //공백 처리
        String processedKeyword = keyword.replaceAll("\\s+", "");

        List<Accommodation> accommodations = accommodationRepository.findKeyword(processedKeyword);
        List<AccommodationDto> accommodationDtos = new ArrayList<>();



        for (Accommodation accommodation : accommodations){
            AccommodationDto accommodationDto = AccommodationDto.toAccommodationDto(accommodation);
            accommodationDtos.add(accommodationDto);
        }
        return accommodationDtos;

    }
}

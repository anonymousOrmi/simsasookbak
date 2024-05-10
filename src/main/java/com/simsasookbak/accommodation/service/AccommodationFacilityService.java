package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.AccommodationFacility;
import com.simsasookbak.accommodation.repository.AccommodationFacilityRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccommodationFacilityService {

    private final AccommodationFacilityRepository accommodationFacilityRepository;

    public List<AccommodationFacility> findAll() {
        return accommodationFacilityRepository.findAll();
    }
}

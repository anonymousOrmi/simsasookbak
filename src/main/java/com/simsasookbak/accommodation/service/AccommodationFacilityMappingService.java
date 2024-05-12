package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.domain.AccommodationFacility;
import com.simsasookbak.accommodation.domain.AccommodationFacilityMapping;
import com.simsasookbak.accommodation.repository.AccommodationFacilityMappingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccommodationFacilityMappingService {

    private final AccommodationFacilityService accommodationFacilityService;
    private final AccommodationFacilityMappingRepository accommodationFacilityMappingRepository;


    public void registerMapping(Accommodation accommodation, List<String> facilityList) {
        for (String facility : facilityList) {

            AccommodationFacility accommodationFacility = accommodationFacilityService.findByName(facility);
            AccommodationFacilityMapping accommodationFacilityMapping = new AccommodationFacilityMapping(accommodation,
                    accommodationFacility);

            accommodationFacilityMappingRepository.save(accommodationFacilityMapping);
        }
    }

}

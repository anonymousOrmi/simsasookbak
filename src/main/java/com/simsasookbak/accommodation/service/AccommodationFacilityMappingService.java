package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.domain.AccommodationFacility;
import com.simsasookbak.accommodation.domain.AccommodationFacilityMapping;
import com.simsasookbak.accommodation.repository.AccommodationFacilityMappingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccommodationFacilityMappingService {

    private final AccommodationFacilityService accommodationFacilityService;
    private final AccommodationFacilityMappingRepository accommodationFacilityMappingRepository;


    public void registerMapping(Accommodation accommodation, List<String> facilityList) {
        for (String facility : facilityList) {

            AccommodationFacility accommodationFacility = accommodationFacilityService.findByName(facility);

            if(accommodationFacility == null){
                 accommodationFacility = accommodationFacilityService.save(facility);
                 log.info(String.valueOf(accommodationFacility));
            }

            AccommodationFacilityMapping accommodationFacilityMapping = new AccommodationFacilityMapping(accommodation,
                    accommodationFacility);

            accommodationFacilityMappingRepository.save(accommodationFacilityMapping);
        }
    }

    public void deleteMapping(Long accommodationId) {
        accommodationFacilityMappingRepository.deleteAccommodationFacilityMappingByAccommodationId(accommodationId);
    }

}

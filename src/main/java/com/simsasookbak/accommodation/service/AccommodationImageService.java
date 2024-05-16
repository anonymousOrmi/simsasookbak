package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.AccommodationImage;
import com.simsasookbak.accommodation.repository.AccommodationImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationImageService {

    private final AccommodationImageRepository accommodationImageRepository;

    public AccommodationImageService(AccommodationImageRepository accommodationImageRepository) {
        this.accommodationImageRepository = accommodationImageRepository;
    }

    public void saveAccommodationImage(AccommodationImage accommodationImage){
        accommodationImageRepository.save(accommodationImage);
    }
}

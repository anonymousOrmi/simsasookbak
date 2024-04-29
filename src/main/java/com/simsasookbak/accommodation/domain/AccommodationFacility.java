package com.simsasookbak.accommodation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AccommodationFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_facility_id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;
}

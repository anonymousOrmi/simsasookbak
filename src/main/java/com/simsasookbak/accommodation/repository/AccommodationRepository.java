package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.response.AccommodationView;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository  extends JpaRepository<Accommodation, Long> {

    @Query("select room.accommodation.id as accommodationId, "
            + "room.accommodation.name as name, "
            + "room.accommodation.address as address, "
            + "room.accommodation.region as region, "
            + "min(room.cost) as cost, "
            + "avg(coalesce(review.score, 0.0)) as score, "
            + "image.url as imageUrl "
            + "from Room room "
            + "join room.accommodation "
            + "left join Review review on room.accommodation.id = review.accommodation.id "
            + "left join AccommodationImage image on room.accommodation.id = image.accommodation.id "
            + "where room.isDeleted = false "
            + "and room.accommodation.isDeleted = false "
            + "and review.isDeleted = false "
            + "and (room.accommodation.region =:keyword or room.accommodation.name like %:keyword%) "
            + "group by room.accommodation.id, image.url ")
    Page<AccommodationView> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // inline view 사용을 위한 native 쿼리 사용 (JPQL에서는 미지원)
    @Query(
        value = "select  A.accommodation_id as accommodationId, "
                + "        min(coalesce(A.cost, 0)) as cost, "
                + "        AA.region as region, "
                + "        AA.name as name, "
                + "        AA.address as address, "
                + "        avg(coalesce(R.score, 0.0)) as score, "
                + "        AI.url as imageUrl "
                + "from accommodation AA   "
                + "join room A on AA.accommodation_id = A.accommodation_id   "
                + "left join review R on AA.accommodation_id = R.accommodation_id   "
                + "left join accommodation_image AI on AA.accommodation_id = AI.accommodation_id "
                + "where 1=1   "
                + "and A.is_deleted = false   "
                + "and AA.is_deleted = false   "
                + "or A.room_id not in (   "
                + "    select B.room_id   "
                + "    from reservation B   "
                + "    where (B.start_date <= :startDate and B.end_date >= :endDate) "
                + "        or B.status = '취소' "
                + ") "
                + "and (AA.region = :keyword or AA.name like %:keyword%) "
                + "group by A.accommodation_id,   "
                + "         AA.region, "
                + "         AA.address, "
                + "         AA.name, "
                + "         AI.url ",
        nativeQuery = true
    )
    Page<AccommodationView> findAllByStartDateAndEndDate(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    @Query("SELECT a FROM Accommodation a WHERE a.id = :id AND a.isDeleted = false")
    Accommodation findAccommodationById(@Param("id") Long id);

    @Query("SELECT a.url FROM AccommodationImage a WHERE a.accommodation.id = :accommodationId")
    List<String> findImgByAccommodationId(@Param("accommodationId") Long accommodationId);

    @Query("SELECT a.accommodationFacility.name FROM AccommodationFacilityMapping a WHERE a.accommodation.id = :id")
    List<String> findAccommodationFacilityById(Long id);

    List<Accommodation> findAccommodationByMemberIdAndIsDeletedFalse(@Param("memberId") Long memberId);

    @Query("SELECT a FROM Accommodation a "
            + "WHERE FUNCTION('HOUR', a.createdAt) = FUNCTION('HOUR', :currentTime)"
            + " AND a.isDeleted = false")
    List<Accommodation> findAccommodationsByCreatedAtTime(LocalTime currentTime);
}

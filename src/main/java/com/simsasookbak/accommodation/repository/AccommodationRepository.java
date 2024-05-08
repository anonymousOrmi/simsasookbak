package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.response.AccommodationView;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository  extends JpaRepository<Accommodation, Long> {

    @Query("select accommodation.id as accommodationId, "
        + "accommodation.name as name, "
        + "accommodation.address as address, "
        + "accommodation.region as region,"
        + "min(room.cost) as cost, "
        + "avg(coalesce(review.score, 0.0)) as score "
        + "from Accommodation accommodation "
        + "join Room room on accommodation.id = room.accommodation.id "
        + "left join Review review on accommodation.id = review.accommodation.id "
        + "where accommodation.isDeleted = false "
        + "and room.isDeleted = false "
        + "and (review.isDeleted = null or review.isDeleted = false) "
        + "and (accommodation.region =:keyword or accommodation.name like %:keyword%) "
        + "group by room.cost, "
        + "review.score, "
        + "accommodation.id, "
        + "accommodation.region, "
        + "accommodation.name, "
        + "accommodation.address "
    )
    Page<AccommodationView> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // inline view 사용을 위한 native 쿼리 사용 (JPQL에서는 미지원)
    @Query(
        value = "select  A.accommodation_id as accommodationId, "
                + "        min(coalesce(A.cost, 0)) as cost, "
                + "        AA.region as region, "
                + "        AA.name as name, "
                + "        AA.address as address, "
                + "        avg(coalesce(R.score, 0.0)) as score "
                + "from accommodation AA   "
                + "join room A on AA.accommodation_id = A.accommodation_id   "
                + "left join review R on AA.accommodation_id = R.accommodation_id   "
                + "where 1=1   "
                + "and A.is_deleted is false   "
                + "and AA.is_deleted is false   "
                + "and A.room_id not in (   "
                + "    select B.room_id   "
                + "    from reservation B   "
                + "    where (B.start_date <= :startDate and B.end_date >= :endDate) "
                + "        or B.status = '취소' "
                + ") "
                + "and (AA.region = :keyword or AA.name like %:keyword%) "
                + "group by A.accommodation_id,   "
                + "         A.cost, "
                + "         R.score, "
                + "         AA.region, "
                + "         AA.address, "
                + "         AA.name ",
        nativeQuery = true
    )
    Page<AccommodationView> findAllByStartDateAndEndDate(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    Accommodation findAccommodationById(Long id);

    @Query("SELECT a.url FROM AccommodationImage a WHERE a.accommodation.id = :acom_id")
    List<String> findImgByAcomId(@Param("acom_id") Long acom_id);

    @Query("SELECT a.accommodationFacility.name FROM AccommodationFacilityMapping a WHERE a.accommodation.id = :id")
    List<String> findAccommodationFacilityById(Long id);

}

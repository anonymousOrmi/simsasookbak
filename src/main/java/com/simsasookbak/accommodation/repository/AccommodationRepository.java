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

    @Query(
        value = "select  T.accommodation_id as accommodationId, "
                + "        T.region as region, "
                + "        T.name as name, "
                + "        T.score as score, "
                + "        T.address as address, "
                + "        T.imageUrl as imageUrl, "
                + "        min(R.cost) as cost "
                + "from ("
                + "         select"
                + "                 A.*,"
                + "                avg(coalesce(RI.score, 0.0)) as score, "
                + "                image.url                    as imageUrl "
                + "         from accommodation A"
                + "         left join Review RI on A.accommodation_id = RI.accommodation_id "
                + "         left join accommodation_image image on A.accommodation_id = image.accommodation_id "
                + "         where A.is_deleted = false "
                + "           and (region = :keyword or name like %:keyword% ) "
                + "         group by A.accommodation_id, image.url ) T "
                + "join room R on T.accommodation_id = R.accommodation_id "
                + "where R.is_deleted = false "
                + "group by T.accommodation_id, T.imageUrl, T.score ",
        nativeQuery = true
    )
    Page<AccommodationView> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // inline view 사용을 위한 native 쿼리 사용 (JPQL에서는 미지원)
    @Query(value = "select  A.accommodation_id as accommodationId, "
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

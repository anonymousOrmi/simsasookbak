package com.simsasookbak.member.service;
import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.Role;
import com.simsasookbak.member.domain.Status;
import com.simsasookbak.member.repository.MemberRepository;
import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.repository.ReviewRepository;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final MemberRepository memberRepository;
    private final AccommodationRepository accommodationRepository;
    private final ReviewRepository reviewRepository;
    private final RoomRepository roomRepository;



    public Page<Member> findAllMembersPaged(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }



    //유저 이름 검색
    public List<Member> searchMemberByName(String name) {
        return memberRepository.getSearchMemberByName(name)
                .orElseThrow(() -> new NoSuchElementException("No member found with name: " + name));
    }

    @Transactional
    public void deleteMember(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));

        if(member.getRole().equals(Role.BUSINESS)){
            List<List<Room>> roomList = new ArrayList<>();
            List<Accommodation> accommodationList = accommodationRepository.findAllByMember_Id(member.getId());
            for (Accommodation accommodation : accommodationList) {
                accommodation.changeToDelete();
                roomList.add(roomRepository.findRoomsByAcomId(accommodation.getId()));
            }
            for (List<Room> list : roomList) {
                list.forEach(Room::changeToDelete);
            }
        }
        deleteReview(member.getId());
        member.cancellation();
    }

    @Transactional
    public void deleteReview(Long memberId){
        List<Review> reviewList = reviewRepository.findAllByMember_Id(memberId);
        reviewList.forEach(Review::changeToDelete);

    }






}

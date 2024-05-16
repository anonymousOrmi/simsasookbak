package com.simsasookbak.member.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.Role;
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
import org.springframework.data.domain.Page;
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

    public Page<Member> searchMemberByNamePaged(String name, Pageable pageable) {
        return memberRepository.getSearchMemberByName(name, pageable)
                .orElseThrow(() -> new NoSuchElementException("No member found with name: " + name));
    }

    @Transactional
    public void deleteMember(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
        deleteReview(member.getId());
        member.cancellation();
    }

    @Transactional
    public void deleteReview(Long memberId) {
        List<Review> reviewList = reviewRepository.findAllByMember_Id(memberId);
        reviewList.forEach(Review::changeToDelete);
    }

    public void saveRole(Long memberId, Role newRole) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));

        member.updateRole(newRole);
        memberRepository.save(member);
    }
}

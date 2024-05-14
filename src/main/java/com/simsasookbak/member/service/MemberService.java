package com.simsasookbak.member.service;


import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import com.simsasookbak.member.domain.Role;
import com.simsasookbak.member.repository.MemberRepository;
import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.repository.ReviewRepository;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final AccommodationRepository accommodationRepository;
    private final RoomRepository roomRepository;
    private final ReviewRepository reviewRepository;
    private final BCryptPasswordEncoder encoder;

    public void register(Member member) {
        Member member1 = new Member(member.getEmail(), member.getName(), encoder.encode(member.getPassword()),
                member.getRole().toString(), member.getBirthDate(), member.getStatus(), member.getPhone());
        memberRepository.save(member1);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    public boolean isInDb(String email){
        return memberRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void updateMemberInfo(Long id, String name, String phone) {
        Member member = memberRepository.findUserById(id);
        member.editInfo(name, phone);
    }

    @Transactional
    public void deleteMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();
        if(member.getRole().equals(Role.BUSINESS)){
            List<List<Room>> roomList = new ArrayList<>();
            List<Accommodation> accommodationList = accommodationRepository.findAllByMember_Id(member.getId());
            accommodationList.stream().forEach(x->{
                x.changeToDelete();
                roomList.add(roomRepository.findRoomsByAcomId(x.getId()));
            });
            roomList.stream().forEach(list->list.stream().forEach(Room::changeToDelete));
        }
        deleteReview(member.getId());
        member.cancellation();
    }

    @Transactional
    public void deleteReview(Long memberId){
        List<Review> reviewList = reviewRepository.findAllByMember_Id(memberId);
        reviewList.stream().forEach(review -> review.changeToDelete());

    }

    public boolean checkLogin(String password,String email){
        Member member = memberRepository.findByEmail(email).orElse(new Member());
        return new BCryptPasswordEncoder().matches(password,member.getPassword());
    }


    public String makeRandomInt(){
        int i=0;
        i = (int) (Math.random()*10000000);
        return String.valueOf(i);
    }



}

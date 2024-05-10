package com.simsasookbak.email.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.email.domain.MailType;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.room.domain.Room;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MailForm {
    final String to;
    final String title;
    final String subTitle;
    final String accommodationName;
    final String roomName;
    final LocalDateTime checkIn;
    final LocalDateTime checkout;
    final String address;
    final String tel;
    final String email;

    public MailForm(MailType type, Reservation reservation) {
        Accommodation accommodation = reservation.getAccommodation();
        Member member = reservation.getMember();
        Room room = reservation.getRoom();

        this.to = member.getEmail();
        this.title = type.getSubject();
        this.subTitle = type.getMessage(member.getName());
        this.accommodationName = accommodation.getName();
        this.roomName = room.getName();
        this.checkIn = LocalDateTime.of(reservation.getStartDate(), accommodation.getCheckIn());
        this.checkout = LocalDateTime.of(reservation.getEndDate(), accommodation.getCheckOut());
        this.address = accommodation.getAddress();
        this.tel = accommodation.getMember().getPhone();
        this.email = accommodation.getMember().getEmail();
    }
}

package com.simsasookbak.accommodation.controller;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.review.domain.ExternalSummary;
import com.simsasookbak.room.domain.Room;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;

    //상세 페이지 조회 (영석)
    @GetMapping("/{acom_id}")
    public String details(@PathVariable Long acom_id){
        Accommodation accommodation = accommodationService.findAccommodationById(acom_id);
        List<Room> roomList = accommodationService.findRoomByAcomId(acom_id);
        String exSummary = accommodationService.findExSummaryByAcomId(acom_id);
        String inSummary = accommodationService.findInSummaryByAcomId(acom_id);
        List<String> imgList = accommodationService.findImgByAcomId(acom_id);



        return "details";
    }

    //예약 페이지 연결 (상형)
    @GetMapping("/{acom_id}/{room_id}/reservation")
    public void reservation(@PathVariable Integer acom_id,@PathVariable Integer room_id){

    }

    //예약 성공 메세지 전송 (상형)
    @PostMapping("/reservation/message")
    public void reservationMessage(){

    }

    //댓글 작성 (영석)
    @PostMapping("/{acom_id}/comment")
    public void review(@PathVariable Integer acom_id){

    }
}

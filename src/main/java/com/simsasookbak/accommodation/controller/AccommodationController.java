package com.simsasookbak.accommodation.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.simsasookbak.accommodation.domain.AccommodationImage;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.dto.AccommodationUpdateDto;
import com.simsasookbak.accommodation.dto.request.AccommodationAndRoomsAddRequestDto;
import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationAddResponseDto;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.service.AccommodationImageService;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.global.aop.MethodInvocationLimit;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.dto.response.ReservationResponse;
import com.simsasookbak.reservation.service.ReservationService;
import com.simsasookbak.review.dto.ReviewDto;
import com.simsasookbak.review.service.ReviewService;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("accommodation")
@Slf4j
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final RoomService roomService;
    private final ReviewService reviewService;
    private final ReservationService reservationService;
    private final AmazonS3Client amazonS3Client;
    private final AccommodationImageService accommodationImageService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    @GetMapping
    public String showAccommodations(
            @ModelAttribute AccommodationRequest request,
            @RequestParam(value = "page", defaultValue = "0") int pageNum,
            Model model
    ) {
        Page<AccommodationResponse> response = accommodationService.searchAccommodations(request, pageNum);
        model.addAttribute("currentPage", response.getNumber());
        model.addAttribute("totalPages", response.getTotalPages());

        model.addAttribute("request", request);
        model.addAttribute("accommodations", response);

        return "list-page";
    }

    //상세 페이지 조회 (영석)
    @GetMapping("/{accommodationId}")
    public String details(
            @AuthenticationPrincipal Member member,
            @PathVariable Long accommodationId,
            Model model
    ) {
        boolean isNotExistReservation = reservationService.isNotExistReservation(
                accommodationId,
                member.getId()
        );

        if (isNotExistReservation) {
            model.addAttribute("reservation", new ReservationResponse());
        }

        AccommodationDto accommodation = accommodationService.findAccommodationById(accommodationId);
        List<RoomDto> roomList = roomService.findRoomByAccommodationId(accommodationId);
        String exSummary = reviewService.findExSummaryByAcomId(accommodationId);
        String inSummary = reviewService.findInSummaryByAcomId(accommodationId);
        List<ReviewDto> reviewList = reviewService.findAllReviewByAcomId(accommodationId);
        List<String> imgList = accommodationService.findImgByAccommodationId(accommodationId);

        model.addAttribute("accommodation", accommodation);
        model.addAttribute("roomList", roomList);
        model.addAttribute("exSummary", exSummary);
        model.addAttribute("inSummary", inSummary);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("imgList", imgList);

        return "details";
    }


    //리뷰 등록 페이지 이동
    @GetMapping("/{accommodationId}/comment")
    public String review(
            @AuthenticationPrincipal Member member,
            @PathVariable Long accommodationId,
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        if (id != null) {
            ReviewDto reviewWithImages = reviewService.findReviewById(id, member.getId(), accommodationId);
            model.addAttribute("reviewWithImages", reviewWithImages);
        }

        List<String> roomNames = reservationService.getReservationRoomName(accommodationId, member.getId());
        if (roomNames.isEmpty()) {
            return "redirect:/accommodation/{accommodationId}";
        }

        model.addAttribute("accommodation", accommodationId);
        model.addAttribute("member", member.getId());
        model.addAttribute("RoomNames", roomNames);
        return "review-register";
    }


    @MethodInvocationLimit
    @PostMapping("/registerPage/register")
    public ResponseEntity<Long> register(@AuthenticationPrincipal Member member,
                                         @RequestBody AccommodationAndRoomsAddRequestDto accommodationAndRoomsAddRequestDto) {
        AccommodationAddResponseDto response = accommodationService.save(member, accommodationAndRoomsAddRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response.getAccommodationId());
    }

    @PostMapping("/image/file/register/{responseAccommodationId}")
    public ResponseEntity<Void> uploadAccommodationImage(@ModelAttribute MultipartFile[] file,
                                                         @PathVariable Long responseAccommodationId) {
        if (file != null && !Arrays.stream(file).filter(x -> !Objects.equals(x.getOriginalFilename(), "")).toList()
                .isEmpty()) {
            try {
                String[] fileNames = new String[file.length];
                String[] fileUrls = new String[file.length];
                ObjectMetadata[] metadata = new ObjectMetadata[file.length];
                for (int i = 0; i < file.length; i++) {
                    metadata[i] = new ObjectMetadata();
                }
                for (int i = 0; i < file.length; i++) {
                    fileNames[i] = file[i].getOriginalFilename();
                    fileUrls[i] = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + fileNames[i];
                    metadata[i].setContentType(file[i].getContentType());
                    metadata[i].setContentLength(file[i].getSize());
                    amazonS3Client.putObject(bucket, fileNames[i], file[i].getInputStream(), metadata[i]);
                    accommodationImageService.saveAccommodationImage(AccommodationImage.builder().url(fileUrls[i])
                            .accommodation(accommodationService.findById(responseAccommodationId)).build());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok().build();
    }

    @MethodInvocationLimit
    @PutMapping("/{accommodationId}/accommodationUpdate")
    public ResponseEntity<Long> updateAccommodation(@AuthenticationPrincipal Member member,
                                                    @PathVariable Long accommodationId, @RequestBody
                                                    AccommodationUpdateDto accommodationUpdateDto) {

        accommodationService.updateAccommodation(member, accommodationId, accommodationUpdateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(accommodationId);
    }

    @DeleteMapping("/{accommodationId}/delete")
    public ResponseEntity<Void> deleteAccommodation(@AuthenticationPrincipal Member member,
                                                    @PathVariable Long accommodationId) {
        accommodationService.deleteAccommodation(member, accommodationId);

        return ResponseEntity.noContent().build();
    }
}

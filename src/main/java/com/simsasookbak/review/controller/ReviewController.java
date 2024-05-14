package com.simsasookbak.review.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.service.ReviewService;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostMapping("/review/register/{accommodationId}")
    public String registReview(
            @ModelAttribute Review review,
            MultipartFile[] file,
            @PathVariable Long accommodationId
    ){
        Review savedReview = review;
        if (savedReview.getId() != null) {
            savedReview = reviewService.modify(review.getId(), review.getContent(), review.getScore());
        } else {
            savedReview = reviewService.registComment(review);
        }

        if(!Arrays.stream(file).filter(x-> !Objects.equals(x.getOriginalFilename(), "")).toList().isEmpty()) {
            try {
                String[] fileNames = new String[file.length];
                String[] fileUrls = new String[file.length];
                ObjectMetadata[] metadata = new ObjectMetadata[file.length];
                for (int i = 0; i < file.length; i++) {
                    metadata[i] = new ObjectMetadata();
                }
                for(int i =0; i< file.length; i++){
                    fileNames[i] = file[i].getOriginalFilename();
                    fileUrls[i] = "https://" + bucket +  ".s3." + region + ".amazonaws.com/" + fileNames[i];
                    metadata[i].setContentType(file[i].getContentType());
                    metadata[i].setContentLength(file[i].getSize());
                }

                for(int i=0;i< fileNames.length;i++){
                    amazonS3Client.putObject(bucket, fileNames[i], file[i].getInputStream(), metadata[i]);
                    reviewService.registReviewImage(fileUrls[i], savedReview.getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/accommodation/{accommodationId}";
    }


}

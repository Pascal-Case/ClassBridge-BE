package com.linked.classbridge.controller;

import com.linked.classbridge.dto.SuccessResponse;
import com.linked.classbridge.dto.oneDayClass.ClassDto.ClassResponseByUser;
import com.linked.classbridge.dto.review.GetReviewResponse;
import com.linked.classbridge.service.OneDayClassService;
import com.linked.classbridge.service.ReviewService;
import com.linked.classbridge.service.UserService;
import com.linked.classbridge.type.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/class")
public class OneDayClassController {

    private final ReviewService reviewService;
    private final OneDayClassService oneDayClassService;
    private final UserService userService;

    @Operation(summary = "클래스 리뷰 조회", description = "클래스 리뷰 조회")
    @GetMapping("/{classId}/reviews")
    public ResponseEntity<SuccessResponse<Page<GetReviewResponse>>> getClassReviews(
            @PathVariable Long classId,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok().body(
                SuccessResponse.of(
                        ResponseMessage.REVIEW_GET_SUCCESS,
                        reviewService.getClassReviews(classId, pageable)
                )
        );
    }

    @Operation(summary = "클래스 상세 조회", description = "클래스 상세 조회")
    @GetMapping("/{classId}")
    public ResponseEntity<SuccessResponse<ClassResponseByUser>> getClassDetail(
            @PathVariable Long classId
    ) {
        return ResponseEntity.ok().body(
                SuccessResponse.of(
                        ResponseMessage.ONE_DAY_CLASS_GET_SUCCESS,
                        oneDayClassService.getOneDayClassByUser(userService.checkLogin() ? userService.getCurrentUserEmail() : null, classId)
                )
        );
    }
}

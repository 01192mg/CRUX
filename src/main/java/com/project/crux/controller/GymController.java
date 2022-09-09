package com.project.crux.controller;

import com.project.crux.domain.response.ResponseDto;
import com.project.crux.security.jwt.UserDetailsImpl;
import com.project.crux.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GymController {

    private final GymService gymService;

    //api 인기 클라이밍짐 조회
    @GetMapping("/gyms/popular")
    public ResponseDto<?> getPopularGyms(@PageableDefault(sort = "avgScore", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseDto.success(gymService.getPopularGyms(pageable));
    }

    //api 클라이밍짐 검색 조회
    @GetMapping("/gyms/search")
    public ResponseDto<?> getSearchGyms(@RequestParam String query, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseDto.success(gymService.getSearchGyms(query,pageable));
    }

    //api 짐 상세 조회
    @GetMapping("/gyms/{gymId}")
    public ResponseDto<?> getGym(@PathVariable Long gymId) {
        return ResponseDto.success(gymService.getGym(gymId));
    }

    //api 짐 즐겨찾기 추가,삭제
    @PostMapping("/likegyms/{gymId}")
    public ResponseDto<?> likeGym(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long gymId){
        return ResponseDto.success(gymService.likeGym(userDetails,gymId));
    }
}

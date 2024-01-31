package org.ssafy.pasila.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.member.dto.request.PersonalInfoRequest;
import org.ssafy.pasila.domain.member.dto.ChannelDTO;
import org.ssafy.pasila.domain.member.dto.PersonalInfoDTO;
import org.ssafy.pasila.domain.member.repository.ChannelRepository;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.PersonalInfoRepository;
import org.ssafy.pasila.domain.member.service.MemberService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@Tag(name = "Member", description = "Member API")
public class MemberController {

    private final PersonalInfoRepository personalInfoRepository;
    private final ChannelRepository channelRepository;
    private final MemberService memberService;


    //유효한 이메일인지 확인하는 부분 추가
    @GetMapping("/member/email")
    public ResponseEntity<?> checkEmail(@RequestParam String email){

        if(memberService.checkEmail(email)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/member/channel")
    public ResponseEntity<?> checkChannel(@RequestParam String channel){
        if(memberService.checkChannel(channel)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/member/join" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> join(@RequestPart(value = "member") Member member ,
                                  @RequestPart(value = "profileFile" , required = false) MultipartFile profileFile ){
        try {
            memberService.join(member, profileFile);
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }catch (Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    // 마이페이지(personal-info)

    // 회원 정보 조회 by id
    @Operation(summary = "get member by id", description = "마이페이지 - id로 회원 정보 조회")
    @GetMapping("/member/{id}")
    public ApiCommonResponse<PersonalInfoDTO> getMember(@PathVariable("id") Long id){
        PersonalInfoDTO result = personalInfoRepository.findById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);
    }

    // 회원 정보 수정
    @Operation(summary = "update member", description = "마이페이지 - 회원 정보 수정")
    @PutMapping("/member/{id}")
//    public ResponseEntity<?> updateMember(@PathVariable("id") Long id,
//                                          @RequestPart(value = "pr") PersonalInfoRequest request,
//                                          @RequestPart(value = "new_image", required = false) MultipartFile newImageName) {
//        try {
//            memberService.updateMember(id, request, newImageName);
//        } catch (Exception e) {
//            return response.exceptionHandler(500, e);
//        }
//        return response.handleSuccess(200, "success");
//    }
    public ResponseEntity<String> updateMember(@PathVariable("id") Long id,
                                               @RequestPart(value = "pr") PersonalInfoRequest request,
                                               @RequestPart(value = "new_image", required = false) MultipartFile newImageName) {
        try {
            memberService.updateMember(id, request, newImageName);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    // 채널(Channel)

    // 채널 정보 조회 by id
    @Operation(summary = "get channel by id", description = "채널 - id로 채널 정보 조회")
    @GetMapping("/member/channel/{id}")
    public ApiCommonResponse<ChannelDTO> getChannel(@PathVariable("id") Long id) {
        ChannelDTO result = channelRepository.findById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);
    }

    // 채널 설명 수정 by id
    @Operation(summary = "update channel desc by id", description = "채널 - id를 기준으로 하여 채널 설명 수정")
    @PutMapping("/member/channel/{id}")
    public ApiCommonResponse<?> updateChannel(@PathVariable("id") Long id,
                                              @RequestBody String description) {
        Long updatedId = memberService.updateChannel(id, description);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), updatedId);
    }
}

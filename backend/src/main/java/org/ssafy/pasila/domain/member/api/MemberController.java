package org.ssafy.pasila.domain.member.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.auth.dto.request.LoginRequestDto;
import org.ssafy.pasila.domain.auth.service.EncryptService;
import org.ssafy.pasila.domain.member.dto.*;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.ChannelRepository;
import org.ssafy.pasila.domain.member.repository.PersonalInfoRepository;
import org.ssafy.pasila.domain.member.service.MemberService;
import org.ssafy.pasila.domain.product.dto.ProductResponseDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/member")
@Tag(name = "Member", description = "Member API")
public class MemberController {

    private final PersonalInfoRepository personalInfoRepository;

    private final ChannelRepository channelRepository;

    private final MemberService memberService;

    private final EncryptService encryptService;

    @Operation(summary = "Check Emmail", description = "중복 이메일 확인")
    @GetMapping("/email")
    public ApiCommonResponse<?> checkEmail(@RequestParam String email) {

        if (memberService.checkEmail(email)) {
            return ApiCommonResponse.successResponse(HttpStatus.OK.value(), true);
        } else {
            return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value(), false);
        }

    }

    @Operation(summary = "Check Channel name", description = "채널명 중복 확인")
    @GetMapping("/channel")
    public ApiCommonResponse<?> checkChannel(@RequestParam String channel) {
        if (memberService.checkChannel(channel)) {
            return ApiCommonResponse.successResponse(HttpStatus.OK.value(), true);
        } else {
            return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value(), false);
        }
    }

    @Operation(summary = "Join member", description = "회원 가입")
    @PostMapping(value = "/join", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiCommonResponse<?> join(@RequestPart(value = "member") Member member,
                                  @RequestPart(value = "profileFile", required = false) MultipartFile profileFile) {
        try {
            memberService.join(member, profileFile);
            return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "success");
        } catch (Exception e) {
            String errorMessage = "An error occurred: " + e.getMessage();

            return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        }
    }

    @Operation(summary = "Update password", description = "비밀번호 변경")
    @PostMapping(value = "/updatePw")
    public ApiCommonResponse<?> updatePw(@RequestBody LoginRequestDto requestDto) {
        memberService.updatePassword(requestDto);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "success");
    }

    /**
     * 마이페이지(personal-info)
     */

    // 회원 정보 조회 by id
    @Operation(summary = "Get member by id", description = "id로 회원 정보 조회")
    @GetMapping("/{id}")
    public ApiCommonResponse<PersonalInfoDto> getMember(@PathVariable("id") Long id) {
        PersonalInfoDto result = personalInfoRepository.findById(id);
        if(result.getAccount() != null && !result.getAccount().equals("")){
            result.setAccount(encryptService.decryptAccount(result.getAccount()));
        }
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);
    }

    // 비밀번호 확인
    @Operation(summary = "Check password", description = "마이페이지 정보 수정을 위한 비밀번호 확인")
    @PostMapping("/{id}/pw")
    public ApiCommonResponse<Boolean> checkPw(@PathVariable("id") Long id,
                                              @RequestBody Map<String, String> passwordMap) {
        Boolean isEqual = memberService.checkPW(id, passwordMap.get("password"));
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), isEqual);
    }

    // 회원 정보 수정
    @Operation(summary = "Update member", description = "회원 정보 수정")
    @PutMapping("/{id}")
    public ApiCommonResponse<?> updateMember(@PathVariable("id") Long id,
                                             @RequestPart(value = "personal_info") PersonalInfoDto request,
                                             @RequestPart(value = "new_image", required = false) MultipartFile newImageName) throws IOException {
        Long updatedId = memberService.updateMember(id, request, newImageName);
        return ApiCommonResponse.successResponse(HttpStatus.CREATED.value(), updatedId);
    }

    /**
     * 채널(channel)
     */

    // 채널 정보 조회 by id
    @Operation(summary = "Get channel by id", description = "id로 채널 정보 조회")
    @GetMapping("/channel/{id}")
    public ApiCommonResponse<ChannelDto> getChannel(@PathVariable("id") Long id) {
        ChannelDto result = channelRepository.findById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);
    }

    // 채널 설명 수정 by id
    @Operation(summary = "Update channel desc by id", description = "id를 기준으로 하여 채널 설명 수정")
    @PutMapping("/channel/{id}")
    public ApiCommonResponse<?> updateChannel(@PathVariable("id") Long id,
                                              @RequestBody Map<String, String> descMap) {
        Long updatedId = memberService.updateChannel(id, descMap.get("description"));
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), updatedId);
    }

    // 채널별 Shortping 정보 조회 by id
    @Operation(summary = "Get channel Shortping by id", description = "id로 채널별 숏핑 조회")
    @GetMapping("/channel/{id}/shortping")
    public ApiCommonResponse<List<ChannelShortpingDto>> getChannelShortping(@PathVariable("id") Long id) {
        List<ChannelShortpingDto> results = memberService.getChannelShortpingById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), results);
    }

    // 채널별 Live 정보 조회 by id
//    @Operation(summary = "Get channel live by id", description = "id로 채널별 라이브 조회")
//    @GetMapping("/channel/{id}/live")
//    public ApiCommonResponse<List<ChannelLiveStatusDto>> getChannelLive(@PathVariable("id") Long id) {
//        List<ChannelLiveStatusDto> results = memberService.getChannelLiveStatusById(id);
//        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), results);
//    }

    //채널별 Live 정보 조회
    @Operation(summary = "Get channel live by id", description = "id로 채널별 라이브 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChannelLiveStatusDto.class)))
                    })})
    @GetMapping("/channel/{id}/live")
    public ApiCommonResponse<List<ChannelLiveStatusDto>> getLiveForChannel(@PathVariable("id") Long id) {
        List<ChannelLiveStatusDto> results = memberService.getChannelLiveStatusById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), results);
    }

}

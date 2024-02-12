package org.ssafy.pasila.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.auth.dto.MemberInfoDto;
import org.ssafy.pasila.domain.auth.dto.request.LoginRequestDto;
import org.ssafy.pasila.domain.auth.dto.response.LoginResponseDto;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.global.util.JwtUtil;
import org.ssafy.pasila.global.util.TokenUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final TokenUtil tokenUtil;

    @Transactional
    public LoginResponseDto login(LoginRequestDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        Optional<Member> member = memberRepository.findByEmail(email);
        member.orElseThrow(() -> new UsernameNotFoundException("The email address does not exit."));

        if(!encoder.matches(password, member.get().getPassword())) {
            throw new BadCredentialsException("Invalid email or password.");
        }

        MemberInfoDto info = modelMapper.map(member.get(), MemberInfoDto.class);

        info.setRole("COMMON");

        String accessToken = jwtUtil.createAccessToken(info);

        member.get().addToken(accessToken);

        LoginResponseDto loginResponseDto = modelMapper.map(member.get(),LoginResponseDto.class);

        return loginResponseDto;
    }

    @PreAuthorize("isAuthenticated()")
    public void logout(String accessToken) {

        tokenUtil.setBlackList(accessToken, "accessToken", 1440);

    }

}
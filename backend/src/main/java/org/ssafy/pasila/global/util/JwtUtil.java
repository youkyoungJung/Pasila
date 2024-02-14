package org.ssafy.pasila.global.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.ssafy.pasila.domain.auth.dto.MemberInfoDto;

import java.security.Key;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     * JWT 생성
     * @param member
     * @return JWT String
     */
    public String createAccessToken(MemberInfoDto member) {
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS));

        Claims claims = Jwts.claims();
        claims.put("memberId", member.getId());
        claims.put("email", member.getEmail());
        claims.put("role", member.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("pasila")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    /**
     * Token에서 User ID 추출
     * @param token
     * @return User ID
     */
    public Long getUserId(String token) {
        return validateAndGetclaims(token).get("memberId", Long.class);
    }


    public Claims validateAndGetclaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }


//    /**
//     * JWT 검증
//     * @param token
//     * @return IsValidate
//     */
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            if (tokenUtil.hasKeyBlackList(token)){
//                throw new RuntimeException("Expired JWT Token");
//            }
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            log.info("Invalid JWT Token", e);
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT Token", e);
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT Token", e);
//        } catch (IllegalArgumentException e) {
//            log.info("JWT claims string is empty.", e);
//        }
//        return false;
//    }




//    /**
//     * JWT Claims 추출
//     * @param accessToken
//     * @return JWT Claims
//     */
//    public Claims parseClaims(String accessToken) {
//        try {
//            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(accessToken).getBody();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        }
//    }

}

package kr.hs.entrydsm.raisepercent.global.security.jwt.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TokenResponseTest {

    @Test
    void 토큰_응답() {
        //given
        String refreshToken = "finioanoijxlcnioaybes.uebusvbfaugvfausigfe.iyasvguhdsfasknfkasdufbeiolow";
        String accessToken = "sadfasfdsadsfsfsadfasfesadfa.asdfesadfasedafsd.asdfaesdfadsdasfdsfsfa";

        //when
        TokenResponse response = new TokenResponse(accessToken, refreshToken);

        //then
        assertThat(accessToken).isEqualTo(response.getAccessToken());
        assertThat(refreshToken).isEqualTo(response.getRefreshToken());
    }

}

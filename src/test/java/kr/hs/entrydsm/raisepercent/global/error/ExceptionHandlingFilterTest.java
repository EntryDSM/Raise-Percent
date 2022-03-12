package kr.hs.entrydsm.raisepercent.global.error;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class ExceptionHandlingFilterTest {

    private final ExceptionHandlingFilter filter = new ExceptionHandlingFilter();

    @Test
    void 필터_예외작동_테스트() throws IOException, ServletException {
        //given
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        doThrow(new RaiseException(errorCode))
                .when(filterChain)
                .doFilter(request, response);

        //when
        filter.doFilterInternal(request, response, filterChain);

        //then
        assertEquals(errorCode.getStatus(), response.getStatus());
    }

    @Test
    void 필터_작동_테스트() throws IOException, ServletException {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        //when
        filter.doFilterInternal(request, response, filterChain);

        //then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
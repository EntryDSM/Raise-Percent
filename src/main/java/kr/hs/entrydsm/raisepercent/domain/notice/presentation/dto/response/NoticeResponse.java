package kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NoticeResponse {

    private String title;
    private LocalDateTime createAt;
}

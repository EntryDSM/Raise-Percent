package kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeListResponse {

    private final List<NoticeResponse> noticeList;

}

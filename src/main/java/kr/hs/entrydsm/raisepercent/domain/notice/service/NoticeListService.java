package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeListResponse;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeListService {

    private final NoticeRepository noticeRepository;

    public NoticeListResponse execute() {
        List<NoticeResponse> noticeList = noticeRepository.findAllBy()
                .stream().map(notice -> new NoticeResponse(notice.getTitle(), notice.getCreatedAt()))
                .collect(Collectors.toList());

        return new NoticeListResponse(noticeList);
    }
}

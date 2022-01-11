package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookmarkListResponse {

    private final List<BookmarkElement> companyList;

}

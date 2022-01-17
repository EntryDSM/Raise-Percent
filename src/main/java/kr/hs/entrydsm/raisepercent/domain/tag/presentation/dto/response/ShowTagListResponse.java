package kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ShowTagListResponse {

    private final List<TagListResponse> tagList;
}

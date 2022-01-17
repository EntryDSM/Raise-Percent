package kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TagListResponse {

    private UUID id;

    private String name;

}

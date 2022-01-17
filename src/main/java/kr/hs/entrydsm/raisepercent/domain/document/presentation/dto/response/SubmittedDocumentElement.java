package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubmittedDocumentElement {

    private final String name;
    private final String number;
    private final String submittedDocumentId;

}

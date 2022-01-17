package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SubmittedDocumentListResponse {

    private final List<SubmittedDocumentElement> documentList;

}

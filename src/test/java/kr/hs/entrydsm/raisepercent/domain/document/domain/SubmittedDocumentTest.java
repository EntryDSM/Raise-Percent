package kr.hs.entrydsm.raisepercent.domain.document.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SubmittedDocumentTest {

    private static final Document document = Document.builder()
            .build();

    private static final SubmittedDocument submittedDocument = SubmittedDocument.builder()
            .document(document)
            .build();

    @Test
    void 제출한문서_객체_생성() {
        SubmittedDocument submittedDocument = new SubmittedDocument();
        assertNull(submittedDocument.getId());
        assertNull(submittedDocument.getDocument());
    }

    @Test
    void 제출한문서_문서_가져오기() {
        assertEquals(document, submittedDocument.getDocument());
    }

}
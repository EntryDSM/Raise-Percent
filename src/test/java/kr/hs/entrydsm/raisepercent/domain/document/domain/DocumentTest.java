package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.domain.document.domain.types.DocumentType;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DocumentTest {

    private static final DocumentType DOCUMENT_TYPE = DocumentType.RESUME;

    private static final Student student = Student.builder()
            .build();

    private static final Document document = Document.builder()
            .documentType(DOCUMENT_TYPE)
            .student(student)
            .build();

    @Test
    void 문서_객체_생성() {
        Document document = new Document();
        assertNull(document.getId());
        assertNull(document.getDocumentType());
        assertNull(document.getLastModifiedAt());
        assertNull(document.getStudent());
    }

    @Test
    void 학생_가져오기() {
        assertEquals(student, document.getStudent());
    }

    @Test
    void 종류_가져오기() {
        assertEquals(DOCUMENT_TYPE, document.getDocumentType());
    }

    @Test
    void 포트폴리오() {
        DocumentType documentType = DocumentType.PORTFOLIO;
        Document document = Document.builder()
                .student(student)
                .documentType(documentType)
                .build();
        assertEquals(documentType, document.getDocumentType());
    }

}
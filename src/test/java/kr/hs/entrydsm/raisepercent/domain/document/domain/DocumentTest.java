package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.domain.document.domain.types.DocumentType;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class DocumentTest {

    private final DocumentType DOCUMENT_TYPE = DocumentType.RESUME;

    private final Student student = Student.builder()
            .build();

    private final Document document = Document.builder()
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
        assertThat(student).isEqualTo(document.getStudent());
    }

    @Test
    void 종류_가져오기() {
        assertThat(DOCUMENT_TYPE).isEqualTo(document.getDocumentType());
    }

    @Test
    void 포트폴리오() {
        DocumentType documentType = DocumentType.PORTFOLIO;
        Document document = Document.builder()
                .student(student)
                .documentType(documentType)
                .build();
        assertThat(documentType).isEqualTo(document.getDocumentType());
    }

}
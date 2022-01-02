package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.domain.document.domain.types.Type;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DocumentTest {

    private static final Type type = Type.RESUME;

    private static final Student student = Student.builder()
            .build();

    private static final Document document = Document.builder()
            .type(type)
            .student(student)
            .build();

    @Test
    void 문서_객체_생성() {
        Document document = new Document();
        assertNull(document.getId());
        assertNull(document.getType());
        assertNull(document.getLastModifiedAt());
        assertNull(document.getStudent());
    }

    @Test
    void 학생_가져오기() {
        assertEquals(student, document.getStudent());
    }

    @Test
    void 종류_가져오기() {
        assertEquals(type, document.getType());
    }

    @Test
    void 포트폴리오() {
        Type type = Type.PORTFOLIO;
        Document document = Document.builder()
                .student(student)
                .type(type)
                .build();
        assertEquals(type, document.getType());
    }

}
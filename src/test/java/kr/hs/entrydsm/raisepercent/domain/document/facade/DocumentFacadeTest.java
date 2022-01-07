package kr.hs.entrydsm.raisepercent.domain.document.facade;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.types.Type;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentFacadeTest {

    @Test
    public void 문서_정보_가져오기() {
        UUID id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        Type type = Type.valueOf("RESUME");

        Student student = Student.builder().build();

        Document document = Document.builder()
                .student(student)
                .type(type)
                .build();

        assertEquals(student, document.getStudent());
    }

}

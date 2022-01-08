package kr.hs.entrydsm.raisepercent.domain.tag.facade;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.RegisteredTag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.RegisteredTagRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TagFacadeTest {

    private static final RegisteredTagRepository registeredTagRepository = mock(RegisteredTagRepository.class);

    private static final TagFacade tagFacade = new TagFacade(registeredTagRepository);

    @Test
    void 등록한_태그_가져오기() {
        Student student = Student.builder().build();
        Tag tag = Tag.builder().name("test").build();
        RegisteredTag registeredTag = RegisteredTag.builder()
                .tag(tag).build();


        when(registeredTagRepository.findByStudent(student))
                .thenReturn(List.of(registeredTag));

        List<String> resultList = tagFacade.queryRegisteredTagValue(student);

        for(String result : resultList) {
            assertEquals(tag.getName(), result);
        }

    }

}
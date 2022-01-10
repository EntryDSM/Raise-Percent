package kr.hs.entrydsm.raisepercent.domain.tag.facade;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.RegisteredTag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.RegisteredTagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.AlreadyRegisteredTagException;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.TagNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TagFacadeTest {

    private static final TagRepository tagRepository = mock(TagRepository.class);

    private static final RegisteredTagRepository registeredTagRepository = mock(RegisteredTagRepository.class);

    private static final TagFacade tagFacade = new TagFacade(tagRepository, registeredTagRepository);

    private static final String testUUID = "fa4d262f-c7e0-4f52-9f7f-6d3e83b93612";

    @Test
    void 등록한_태그_가져오기() {
        Student student = Student.builder().build();
        Tag tag = Tag.builder().name(testUUID).build();
        RegisteredTag registeredTag = RegisteredTag.builder()
                .tag(tag).build();


        when(registeredTagRepository.findByStudent(student))
                .thenReturn(List.of(registeredTag));

        List<String> resultList = tagFacade.queryRegisteredTagValue(student);

        for(String result : resultList) {
            assertEquals(tag.getName(), result);
        }

    }

    @Test
    void 학생_태그_등록하기() {
        List<String> tagId = List.of(testUUID);
        Tag tag  = Tag.builder().name(testUUID).build();
        Student student = Student.builder().build();

        when(tagRepository.findById(UUID.fromString(testUUID)))
                .thenReturn(Optional.of(tag));
        when(registeredTagRepository.findByStudentAndTag(any(), any()))
                .thenReturn(Optional.empty());

        tagFacade.registerTag(tagId, student);
    }

    @Test
    void 태그_존재하지_않음() {
        List<String> tagId = List.of(testUUID);
        Student student = Student.builder().build();

        when(tagRepository.findById(UUID.fromString(testUUID)))
                .thenReturn(Optional.empty());
        when(registeredTagRepository.findByStudentAndTag(any(), any()))
                .thenReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> tagFacade.registerTag(tagId, student));
    }

    @Test
    void 이미_등록된_태그() {
        List<String> tagId = List.of(testUUID);
        Tag tag  = Tag.builder().name(testUUID).build();
        Student student = Student.builder().build();
        RegisteredTag registeredTag = RegisteredTag.builder().build();

        when(tagRepository.findById(UUID.fromString(testUUID)))
                .thenReturn(Optional.of(tag));
        when(registeredTagRepository.findByStudentAndTag(any(), any()))
                .thenReturn(Optional.of(registeredTag));

        assertThrows(AlreadyRegisteredTagException.class, () -> tagFacade.registerTag(tagId, student));
    }

}
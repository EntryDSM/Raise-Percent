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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class TagFacadeTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private RegisteredTagRepository registeredTagRepository;

    @InjectMocks
    private TagFacade tagFacade;

    private final String testUUID = "fa4d262f-c7e0-4f52-9f7f-6d3e83b93612";

    @Test
    void 등록한_태그_가져오기() {
        //given
        Student student = Student.builder().build();
        Tag tag = Tag.builder().name(testUUID).build();
        RegisteredTag registeredTag = RegisteredTag.builder()
                .tag(tag).build();

        //when
        given(registeredTagRepository.findByStudent(student))
                .willReturn(List.of(registeredTag));

        List<String> resultList = tagFacade.queryRegisteredTagValue(student);

        //then
        for(String result : resultList) {
            assertEquals(tag.getName(), result);
        }

    }

    @Test
    void 학생_태그_등록하기() {
        //given
        List<String> tagId = List.of(testUUID);
        Tag tag  = Tag.builder().name(testUUID).build();
        Student student = Student.builder().build();

        when(tagRepository.findById(UUID.fromString(testUUID)))
                .thenReturn(Optional.of(tag));
        when(registeredTagRepository.findByStudentAndTag(any(), any()))
                .thenReturn(Optional.empty());

        //when
        tagFacade.registerTag(tagId, student);

        //then
        then(registeredTagRepository).should(times(1)).save(any());
    }

    @Test
    void 태그_존재하지_않음() {
        //given
        List<String> tagId = List.of(testUUID);
        Student student = Student.builder().build();

        given(tagRepository.findById(UUID.fromString(testUUID)))
                .willReturn(Optional.empty());
        given(registeredTagRepository.findByStudentAndTag(any(), any()))
                .willReturn(Optional.empty());

        //when then
        assertThrows(TagNotFoundException.class, () -> tagFacade.registerTag(tagId, student));
    }

    @Test
    void 이미_등록된_태그() {
        //given
        List<String> tagId = List.of(testUUID);
        Tag tag  = Tag.builder().name(testUUID).build();
        Student student = Student.builder().build();
        RegisteredTag registeredTag = RegisteredTag.builder().build();

        given(tagRepository.findById(UUID.fromString(testUUID)))
                .willReturn(Optional.of(tag));
        given(registeredTagRepository.findByStudentAndTag(any(), any()))
                .willReturn(Optional.of(registeredTag));

        //when then
        assertThrows(AlreadyRegisteredTagException.class, () -> tagFacade.registerTag(tagId, student));
    }

}
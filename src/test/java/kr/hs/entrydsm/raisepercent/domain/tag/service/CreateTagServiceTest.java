package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.AlreadyRegisteredTagException;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.request.CreateTagRequest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class CreateTagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private CreateTagService service;

    private final CreateTagRequest request = new CreateTagRequest();

    private final String name = "test";

    @Test
    void 태그_생성() {
        //given
        given(tagRepository.findByName(name))
                .willReturn(Optional.empty());

        setField(request, "name", name);

        //when
        service.execute(request);

        //then
        then(tagRepository).should(times(1)).save(any());
    }

    @Test
    void 태그_생성_예외() {
        //given
        Tag tag = Tag.builder()
                .name(name)
                .build();

        given(tagRepository.findByName(name))
                .willReturn(Optional.of(tag));

        setField(request, "name", name);

        //when then
        assertThrows(AlreadyRegisteredTagException.class, () -> service.execute(request));
    }
}

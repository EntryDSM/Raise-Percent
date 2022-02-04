package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.AlreadyRegisteredTagException;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.request.CreateTagRequest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class CreateTagServiceTest {

    private static final TagRepository tagRepository = mock(TagRepository.class);

    private static final CreateTagService service = new CreateTagService(tagRepository);

    private static final CreateTagRequest request = new CreateTagRequest();

    private static final String name = "test";

    @Test
    void 태그_생성() {
        given(tagRepository.findByName(name))
                .willReturn(Optional.empty());

        setField(request, "name", name);

        service.execute(request);

        verify(tagRepository, times(1)).save(any());
    }

    @Test
    void 태그_생성_예외() {
        Tag tag = Tag.builder()
                .name(name)
                .build();

        given(tagRepository.findByName(name))
                .willReturn(Optional.of(tag));

        setField(request, "name", name);

        assertThrows(AlreadyRegisteredTagException.class, () -> service.execute(request));
    }
}

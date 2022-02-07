package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.TagNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteTagServiceTest {

    private static final TagRepository tagRepository = mock(TagRepository.class);

    private static final DeleteTagService service = new DeleteTagService(tagRepository);

    @Test
    void 태그_삭제() {
        String id = String.valueOf(UUID.randomUUID());
        Tag tag = Tag.builder().build();

        given(tagRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.of(tag));

        service.execute(id);

        verify(tagRepository, times(1)).findById(UUIDUtil.convertToUUID(id));
        verify(tagRepository, times(1)).delete(tag);
    }

    @Test
    void 태그_삭제_예외() {
        String id = String.valueOf(UUID.randomUUID());

        given(tagRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> service.execute(id));
    }
}

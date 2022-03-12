package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.TagNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteTagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private DeleteTagService service;

    @Test
    void 태그_삭제() {
        //given
        String id = String.valueOf(UUID.randomUUID());
        Tag tag = Tag.builder().build();

        given(tagRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.of(tag));

        //when
        service.execute(id);

        //then
        then(tagRepository).should(times(1)).findById(UUIDUtil.convertToUUID(id));
        then(tagRepository).should(times(1)).delete(tag);
    }

    @Test
    void 태그_삭제_예외() {
        //given
        String id = String.valueOf(UUID.randomUUID());

        given(tagRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.empty());

        //when then
        assertThrows(TagNotFoundException.class, () -> service.execute(id));
    }
}

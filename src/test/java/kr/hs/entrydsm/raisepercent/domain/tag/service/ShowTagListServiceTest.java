package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.ShowTagListResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ShowTagListServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private ShowTagListService service;

    @Test
    void 태그_리스트_불러오기() {
        //given
        String name = "test";
        List<Tag> arrayList = new ArrayList<>();

        given(tagRepository.findAllByNameContaining(name))
                .willReturn(arrayList);

        //when
        ShowTagListResponse response = service.execute(name);

        //then
        assertThat(arrayList).isEqualTo(response.getTagList());

        then(tagRepository).should(times(1)).findAllByNameContaining(name);
    }
}

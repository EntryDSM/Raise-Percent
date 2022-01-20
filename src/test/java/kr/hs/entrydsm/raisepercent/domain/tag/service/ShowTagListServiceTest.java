package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.ShowTagListResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowTagListServiceTest {

    private static final TagRepository tagRepository = mock(TagRepository.class);

    private static final ShowTagListService service = new ShowTagListService(tagRepository);

    @Test
    void 태그_리스트_불러오기() {
        String name = "test";
        List<Tag> arrayList = new ArrayList<>();

        when(tagRepository.findAllByNameLike(name))
                .thenReturn(arrayList);

        ShowTagListResponse response = service.execute(name);

        assertThat(arrayList).isEqualTo(response.getTagList());

        verify(tagRepository, times(1)).findAllByNameLike(name);
    }
}

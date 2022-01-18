package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.ShowTagListResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShowTagListServiceTest {

    private static final TagRepository tagRepository = mock(TagRepository.class);

    private static final ShowTagListService service = new ShowTagListService(tagRepository);
    @Test
    void 태그_리스트_불러오기() {
        when(tagRepository.findAll())
                .thenReturn(new ArrayList<>());

        service.execute();

        verify(tagRepository, times(1)).findAllBy();
    }
}

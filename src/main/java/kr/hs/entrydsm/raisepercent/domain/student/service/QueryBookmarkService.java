package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories.BookmarkRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.BookmarkElement;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.BookmarkListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryBookmarkService {

    private final StudentFacade studentFacade;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkListResponse execute() {
        Student student = studentFacade.getCurrentStudent();

        return new BookmarkListResponse(bookmarkRepository.findByStudent(student)
                .stream().map(bookmark -> new BookmarkElement(bookmark.getCompanyName(), bookmark.queryHrEmail()))
                .collect(Collectors.toList()));
    }

}

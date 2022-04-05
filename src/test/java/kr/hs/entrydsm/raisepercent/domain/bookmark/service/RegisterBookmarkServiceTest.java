package kr.hs.entrydsm.raisepercent.domain.bookmark.service;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories.BookmarkRepository;
import kr.hs.entrydsm.raisepercent.domain.bookmark.exception.AlreadyRegisteredBookmarkException;
import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.facade.HrFacade;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

import java.util.Optional;


public class RegisterBookmarkServiceTest {

    private static final BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);

    private static final HrFacade hrFacade = mock(HrFacade.class);

    private static final StudentFacade studentFacade = mock(StudentFacade.class);

    private static final RegisterBookmarkService service = new RegisterBookmarkService(
            bookmarkRepository,
            studentFacade,
            hrFacade);

    @Test
    public void 북마크_등록하기() {
        String studentEmail = "example@dsm.hs.kr";
        String companyName = "DSM";
        Hr hr = Hr.builder()
                .company(
                        Company.builder()
                                .name(companyName)
                                .build()
                )
                .build();
        Student student = Student.builder()
                .user(
                        User.builder()
                                .email(studentEmail)
                                .build()
                )
                .build();

        //given
        given(hrFacade.getHr())
                .willReturn(hr);

        given(studentFacade.getStudent(studentEmail))
                .willReturn(student);

        given(bookmarkRepository.findByHrAndStudent(hr, student))
                .willReturn(Optional.empty());

        //when
        service.execute(studentEmail);

        //then
        then(bookmarkRepository).should(times(1)).save(any());

    }


    @Test
    public void 이미_존재하는_북마크() {
        String studentEmail = "exampleStudent@dsm.hs.kr";

        Hr hr = Hr.builder().build();

        User user = User.builder()
                .email(studentEmail)
                .build();

        Student student = Student.builder()
                .user(user)
                .build();

        Bookmark bookmark = Bookmark.builder()
                .student(student)
                .hr(hr)
                .build();

        //given
        given(hrFacade.getHr())
                .willReturn(hr);

        given(studentFacade.getStudent(studentEmail))
                .willReturn(student);

        given(bookmarkRepository.findByHrAndStudent(hr, student))
                .willReturn(Optional.of(bookmark));

        //then
        assertThrows(AlreadyRegisteredBookmarkException.class, () -> service.execute(studentEmail));

    }

}

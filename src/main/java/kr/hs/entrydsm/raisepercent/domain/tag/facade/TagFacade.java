package kr.hs.entrydsm.raisepercent.domain.tag.facade;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.RegisteredTag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.RegisteredTagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.AlreadyRegisteredTagException;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.TagNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TagFacade {

    private final TagRepository tagRepository;
    private final RegisteredTagRepository registeredTagRepository;

    public List<String> queryRegisteredTagValue(Student student) {
        List<String> list = new ArrayList<>();
        registeredTagRepository.findByStudent(student)
                .forEach(registeredTag ->
                        list.add(registeredTag.getTag().getName())
                );
        return list;
    }

    @Transactional
    public void registerTag(List<String> tagIdList, Student student) {
        for (String tagId : tagIdList) {
            Tag tag = tagRepository.findById(UUID.fromString(tagId))
                    .orElseThrow(() -> TagNotFoundException.EXCEPTION);

            if(registeredTagRepository.findByStudentAndTag(student, tag).isPresent()) {
                throw AlreadyRegisteredTagException.EXCEPTION;
            }

            registeredTagRepository.save(
                    RegisteredTag.builder()
                            .student(student)
                            .tag(tag)
                            .build()
            );
        }
    }

}

package kr.hs.entrydsm.raisepercent.domain.tag.facade;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.RegisteredTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TagFacade {

    private final RegisteredTagRepository registeredTagRepository;

    public List<String> queryRegisteredTagValue(Student student) {
        List<String> list = new ArrayList<>();
        registeredTagRepository.findByStudent(student)
                .forEach(registeredTag ->
                        list.add(registeredTag.getTag().getName())
                );
        return list;
    }

}

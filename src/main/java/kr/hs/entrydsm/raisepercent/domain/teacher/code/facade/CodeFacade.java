package kr.hs.entrydsm.raisepercent.domain.teacher.code.facade;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class CodeFacade {

    public String getRandomCode() {
        return RandomString.make(5);
    }

}

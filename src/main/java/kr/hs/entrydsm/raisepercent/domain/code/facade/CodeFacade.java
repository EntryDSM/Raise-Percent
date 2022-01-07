package kr.hs.entrydsm.raisepercent.domain.code.facade;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class CodeFacade {

    public String getCode() {
        return RandomString.make(5);
    }

}

package kr.hs.entrydsm.raisepercent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RaisePercentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaisePercentApplication.class, args);
    }

}

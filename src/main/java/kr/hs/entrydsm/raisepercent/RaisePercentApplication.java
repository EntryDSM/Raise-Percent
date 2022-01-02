package kr.hs.entrydsm.raisepercent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ConfigurationPropertiesScan
@SpringBootApplication
public class RaisePercentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaisePercentApplication.class, args);
    }

}

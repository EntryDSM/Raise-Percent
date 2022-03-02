package kr.hs.entrydsm.raisepercent.global.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("auth.dsm")
public class DsmAuthProperties {
    private final String format;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;
}


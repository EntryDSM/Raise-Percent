package kr.hs.entrydsm.raisepercent.global.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Base64;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    public static final String ACCESS_TYPE = "ACCESS";
    public static final String REFRESH_TYPE = "REFRESH";

    private static final Integer MICORSECPERSEC = 1000;

    private final String secretKey;
    private final Long accessExp;
    private final Long refreshExp;
    private final String header;
    private final String prefix;

    public JwtProperties(String secretKey, Long accessExp, Long refreshExp, String header, String prefix) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.accessExp = accessExp * MICORSECPERSEC;
        this.refreshExp = refreshExp * MICORSECPERSEC;
        this.header = header;
        this.prefix = prefix;
    }

}

package kr.hs.entrydsm.raisepercent.domain.user.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_user")
public class User {

    @Id
    @Column(length = 60)
    private String email;

    @Column(length = 60)
    private String contactEmail;

    @Column(length = 11)
    private String contactTel;

    @Column(length = 5, nullable = false)
    private String name;

    private String mobileDeviceToken;

    private String webDeviceToken;

    @Builder
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public void setMobileDeviceToken(String mobileDeviceToken) {
        this.mobileDeviceToken = mobileDeviceToken;
    }

    public void setWebDeviceToken(String webDeviceToken) {
        this.webDeviceToken = webDeviceToken;
    }

}

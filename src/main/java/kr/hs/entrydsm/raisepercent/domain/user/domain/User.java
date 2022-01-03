package kr.hs.entrydsm.raisepercent.domain.user.domain;

import kr.hs.entrydsm.raisepercent.global.entity.Person;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class User extends Person {

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

    @Override
    public Type queryType() {
        return null;
    }
}

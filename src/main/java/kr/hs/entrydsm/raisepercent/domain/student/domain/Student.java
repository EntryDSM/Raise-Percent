package kr.hs.entrydsm.raisepercent.domain.student.domain;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.entity.Person;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_student")
@Entity
public class Student {

    @Id
    @Column(length = 60)
    private String email;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    private User user;

    @Column(length = 4, nullable = false)
    private String number;

    @Column(length = 4, nullable = false)
    private String year;

    @Column(length = 20)
    private String position;

    @Builder
    public Student(User user, String number, String year) {
        this.user = user;
        this.number = number;
        this.year = year;
    }

    public void updatePosition(String position) {
        this.position = position;
    }

    @Override
    public Type queryType() {
        return Type.STUDENT;
    }
}

package kr.hs.entrydsm.raisepercent.domain.teacher.domain;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
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
@Table(name = "tbl_teacher")
@Entity
public class Teacher extends Person {

    @Id
    @Column(length = 60)
    private String email;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private Role role;

    @Builder
    public Teacher(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    @Override
    public Type queryType() {
        switch (role) {
            case ROOT:
                return Type.ROOT;
            case TEACHER:
                return Type.TEACHER;
            default:
                return Type.DEFAULT;
        }
    }

}

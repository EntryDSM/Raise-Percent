package kr.hs.entrydsm.raisepercent.domain.teacher.domain;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_teacher")
public class Teacher {

    @Id
    @Column(length = 60)
    private String email;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private Role role;

    @Builder
    public Teacher(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public void updateRole(Role role) {
        this.role = role;
    }

}

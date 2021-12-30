package kr.hs.entrydsm.raisepercent.domain.hr.domain;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
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
@Table(name = "tbl_hr")
@Entity
public class Hr extends Person {

    @Id
    @Column(length = 60)
    private String email;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public Hr(User user, Company company) {
        this.user = user;
        this.company = company;
    }

    @Override
    public Type queryType() {
        if (getRank() == Rank.JUNIOR) {
            return Type.JUNIOR;
        }
        return Type.SENIOR;
    }

    private Rank getRank() {
        return company.getRank();
    }

}

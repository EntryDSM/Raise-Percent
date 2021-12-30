package kr.hs.entrydsm.raisepercent.domain.company.domain;

import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_company")
public class Company extends BaseUUIDEntity {

    @Column(length = 20, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private Rank rank;

    @Builder
    public Company(String name, Rank rank) {
        this.name = name;
        this.rank = rank;
    }

    public void updateRank(Rank rank) {
        this.rank = rank;
    }

}

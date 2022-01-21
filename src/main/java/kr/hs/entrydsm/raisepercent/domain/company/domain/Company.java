package kr.hs.entrydsm.raisepercent.domain.company.domain;

import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_company")
@Entity
public class Company extends BaseUUIDEntity {

    @Column(length = 20, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private Rank rankValue;

    @Builder
    public Company(String name, Rank rankValue) {
        this.name = name;
        this.rankValue = rankValue;
    }

    public void updateRank(Rank rankValue) {
        this.rankValue = rankValue;
    }

}

package kr.hs.entrydsm.raisepercent.domain.tag.domain;

import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_tag")
public class Tag extends BaseUUIDEntity {

    @Column(length = 20)
    private String name;

    @Builder
    public Tag(String name) {
        this.name = name;
    }

}

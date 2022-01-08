package kr.hs.entrydsm.raisepercent.domain.code.domain;

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
@Table(name = "tbl_code")
@Entity
public class Code {

    @Id
    private String id;

    @Column(length = 5, nullable = false)
    private String value;

    @Builder
    public Code(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String updateCode(String value) {
        this.value = value;
        return this.value;
    }

}

package kr.hs.entrydsm.raisepercent.domain.bookmark.domain;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_bookmark")
public class Bookmark extends BaseUUIDTimeEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hr_email")
    private Hr hr;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_email")
    private Student student;

    @Column(length = 20, nullable = false)
    private String companyName;

    @Builder
    public Bookmark(Hr hr, Student student, String companyName) {
        this.hr = hr;
        this.student = student;
        this.companyName = companyName;
    }

}

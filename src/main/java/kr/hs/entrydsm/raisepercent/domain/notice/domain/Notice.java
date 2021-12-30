package kr.hs.entrydsm.raisepercent.domain.notice.domain;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.types.Scope;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_notice")
public class Notice extends BaseUUIDTimeEntity {

    @Column(length = 20, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private Scope scope;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_email")
    private Teacher teacher;

    @Builder
    public Notice(String title, String content, Scope scope, Teacher teacher) {
        this.title = title;
        this.content = content;
        this.scope = scope;
        this.teacher = teacher;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}

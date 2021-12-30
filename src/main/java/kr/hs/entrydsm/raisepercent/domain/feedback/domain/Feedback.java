package kr.hs.entrydsm.raisepercent.domain.feedback.domain;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_feedback")
public class Feedback extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(columnDefinition = "BIT(1)")
    private boolean isWatch;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_email")
    private Teacher teacher;

    @Builder
    public Feedback(String content, boolean isWatch, Document document, Teacher teacher) {
        this.content = content;
        this.isWatch = isWatch;
        this.document = document;
        this.teacher = teacher;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}

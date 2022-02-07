package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.domain.document.domain.types.DocumentType;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_document")
@Entity
public class Document extends BaseUUIDEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_email")
    private Student student;

    @Builder
    public Document(DocumentType documentType, Student student) {
        this.documentType = documentType;
        this.student = student;
    }

}

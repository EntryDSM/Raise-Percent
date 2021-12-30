package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_submitted_document")
@Entity
public class SubmittedDocument extends BaseUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Document document;

    @Builder
    public SubmittedDocument(Document document) {
        this.document = document;
    }

}

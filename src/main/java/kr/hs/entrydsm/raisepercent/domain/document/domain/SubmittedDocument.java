package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_submitted_document")
public class SubmittedDocument extends BaseUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Document document;

    @Builder
    public SubmittedDocument(Document document) {
        this.document = document;
    }

}

package kr.hs.entrydsm.raisepercent.global.entity;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class DocumentContentId implements Serializable {

    @Column(columnDefinition = "TINYINT(4)")
    private int page;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id")
    private Document document;

    @Builder
    public DocumentContentId(int page, Document document) {
        this.page = page;
        this.document = document;
    }

}

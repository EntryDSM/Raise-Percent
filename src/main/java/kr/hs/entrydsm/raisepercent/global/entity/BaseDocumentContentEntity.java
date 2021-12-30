package kr.hs.entrydsm.raisepercent.global.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class BaseDocumentContentEntity {

    @EmbeddedId
    private DocumentContentId id;

    @Column(length = 2000, nullable = false)
    private String content;

}

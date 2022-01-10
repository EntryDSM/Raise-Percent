package kr.hs.entrydsm.raisepercent.global.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class BaseDocumentContentEntity {

    @EmbeddedId
    private DocumentContentId id;

    @Column(length = 2000, nullable = false)
    private String content;

}

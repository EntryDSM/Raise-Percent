package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.BaseDocumentContentEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity(name = "tbl_local_document")
public class LocalDocument extends BaseDocumentContentEntity {

}

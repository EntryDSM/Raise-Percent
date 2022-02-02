package kr.hs.entrydsm.raisepercent.domain.notification.domain;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.types.Type;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.entity.BaseUUIDTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_notification")
@Entity
public class Notification extends BaseUUIDTimeEntity {

    @Column(length = 20, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "BIT(1)", nullable = false)
    private boolean isWatch;

    @Column(length = 20, nullable = false)
    private String icon;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Type documentType;

    @Column(length = 60, nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_email")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_email")
    private User receiver;

    @Builder
    public Notification(String title, String content, boolean isWatch,
                        String icon, Type documentType, String value, User sender, User receiver) {
        this.title = title;
        this.content = content;
        this.isWatch = isWatch;
        this.icon = icon;
        this.documentType = documentType;
        this.value = value;
        this.sender = sender;
        this.receiver = receiver;
    }

    public void checkNotification() {
        this.isWatch = true;
    }

}

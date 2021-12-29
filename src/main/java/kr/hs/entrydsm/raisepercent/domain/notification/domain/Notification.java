package kr.hs.entrydsm.raisepercent.domain.notification.domain;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_notification")
public class Notification {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "BIT(1)", nullable = false)
    private boolean isWatch;

    @Column(length = 20, nullable = false)
    private String icon;

    @Column(length = 20, nullable = false)
    private String type;

    @Column(length = 60, nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_email")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_email")
    private User receiver;

    @Builder
    public Notification(String title, String content, boolean isWatch,
                        String icon, String type, String value, User sender, User receiver) {
        this.title = title;
        this.content = content;
        this.isWatch = isWatch;
        this.icon = icon;
        this.type = type;
        this.value = value;
        this.sender = sender;
        this.receiver = receiver;
    }

}

package ltd.tinyurl.shortlink.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalLink;
    private String shortCode;
    private String shortLink;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String clientIp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
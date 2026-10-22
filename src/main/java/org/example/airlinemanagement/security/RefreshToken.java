package org.example.airlinemanagement.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.airlinemanagement.domain.User;

import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 64)
    private String tokenHash;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private ZonedDateTime expiresAt;

    @Column(nullable = false)
    private boolean revoked;

    public static RefreshToken issue(String tokenHash, User user, ZonedDateTime expiresAt) {
        RefreshToken t = new RefreshToken();
        t.tokenHash = tokenHash;
        t.user = user;
        t.expiresAt = expiresAt;
        return t;
    }

    public void revoke() {
        this.revoked = true;
    }

    public boolean isExpired(ZonedDateTime now) {
        return !expiresAt.isAfter(now);
    }
}
package com.selflearn.ecommerce.registration.token;

import com.selflearn.ecommerce.appuser.AppUser;
import com.selflearn.ecommerce.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Token  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(name = "expired_at", nullable = false)
    private ZonedDateTime expiredAt = ZonedDateTime.now().plusMinutes(15);
    @Column(name = "confirmed_at" )
    private ZonedDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public Token(String token, AppUser appUser) {
        this.token = token;
        this.appUser = appUser;
    }
}

package com.selflearn.ecommerce.appuser;

import com.selflearn.ecommerce.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "app_user")
public class AppUser extends BaseEntity implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id")
    private  Long id;
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false, unique = true)
    private  String username;
    @Column(nullable = false, unique = true)
    private  String email;
    private  String password;
    @Column(name = "app_user_role",nullable = false)
    @Enumerated(EnumType.STRING)
    private  AppUserRole appUserRole;
    @Column(name = "locked",nullable = false)
    private  Boolean locked = false;
    @Column(name = "enabled",nullable = false)
    private  Boolean enabled = false;
    @Column(name = "acct_expired",nullable = false)
    private  Boolean acctExpired = false;
    @Column(name = "credential_expired",nullable = false)
    private  Boolean credentialExpired = false;


    public AppUser(String name, String username, String email, String password, AppUserRole appUserRole) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !acctExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

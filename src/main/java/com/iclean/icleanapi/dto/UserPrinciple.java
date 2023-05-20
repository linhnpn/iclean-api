package com.iclean.icleanapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iclean.icleanapi.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserPrinciple implements UserDetails {
    private Integer id;
    private String username;
    private String fullname;
    private String email;
    private Instant expiredDate;
    @JsonIgnore
    private String password;
    private String profilePicture;
    private String gender;
    public static UserPrinciple build(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleName()));
        return UserPrinciple.builder()
                .id(user.getUserId())
                .fullname(user.getFullname())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .profilePicture(user.getProfilePicture())
                .grantedAuthorities(authorities)
                .gender(user.getGender())
                .build();
    }


    @JsonIgnore
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

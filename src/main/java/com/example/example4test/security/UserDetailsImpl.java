package com.example.example4test.security;

import com.example.example4test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private Long id;

    private String name;

    private String email;

    private String password;



    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserDetailsImpl(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
  // срок действие аккаунта не истек
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
//аккаунт не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
//Срок действия учетных данных не истек
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
//разрешить
    @Override
    public boolean isEnabled() {
        return true;
    }
}

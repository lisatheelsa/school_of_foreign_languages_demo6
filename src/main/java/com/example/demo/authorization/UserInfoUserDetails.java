package com.example.demo.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// извлекает данные пользователей и присваивает им права
public class UserInfoUserDetails implements UserDetails {
    private String name;
    private String password;
    private List<GrantedAuthority> authorities; // поле класса, которое хранит список прав пользователя, предоставленных ему при авторизации;

    public UserInfoUserDetails(UserInfo userInfo) {
        // конструктор класса, который получает объект UserInfo
        // и использует его данные для инициализации полей класса.
        name=userInfo.getName();
        password=userInfo.getPassword();
        authorities= Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    // аннотация, которая сообщает компилятору,
    // что методы, которые следуют за ней, переопределяют методы из класса-родителя UserDetails.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    // метод, который возвращает список прав пользователя;

    @Override
    public String getPassword() {
        return password;
    }
    // метод, который возвращает пароль пользователя;

    @Override
    public String getUsername() {
        return name;
    }
    // метод, который возвращает имя пользователя;

// методы, которые возвращают значения, принимаемые в Spring Security в качестве аргументов.
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

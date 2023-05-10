package com.example.demo.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional; // Импортируем класс Optional из пакета java.util для работы с возможно отсутствующими значениями
// Класс отвечает за загрузку пользователя по имени и возбуждение ошибки, если он не найден
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;
    // Поле repository, которое будет использоваться для доступа к хранилищу пользовательских данных

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        // Вызываем метод findByName из UserInfoRepository для поиска пользователя в хранилище по имени
        // Результатом является объект Optional, который может содержать UserInfo или null

        return userInfo.map(UserInfoUserDetails::new)
                // Используем метод map для создания UserInfoUserDetails
                // которые расширяет UserDetails
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
        // Если объект Optional не содержит UserInfo, то генерируется исключение UsernameNotFoundException
    }
}


package com.example.demo.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//класс сохранения пользователей и сохранения+кодирования их паролей
//тут бизнес логика
// Объявляем класс как сервис, используя аннотацию @Service
@Service
public class UserService {
    // Инъектируем UserInfoRepository в класс через аннотацию @Autowired
    @Autowired
    private UserInfoRepository repository;
    // Инъектируем PasswordEncoder, необходимый для хеширования паролей перед сохранением
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Метод добавления пользователя
    public void addUser(UserInfo userInfo) {
        // Хэшируем пароль пользователя перед сохранением
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        // Вызываем метод save() для сохранения объекта UserInfo в хранилище
        repository.save(userInfo);
    }
}

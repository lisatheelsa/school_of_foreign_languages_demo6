package com.example.demo;//бизнес логика

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // для связи зависимостей из всех классов(иначе проект не соберется)
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; // аннотация для обнаружения всех зависимостей, указывает, что класс StudentService принадлежит серверу SpringBoot

@Service
public class StudentService {
    List<Student> cargoList = null;
    @Autowired
    private StudentRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Student> listAll(String keyword) {/*коллекция, отвечающая за поиск и фильтр*/
        if(keyword != null){
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public void save(Student cargo){
        repo.save(cargo);
    }

    public Student get(Long id){/*редактирование*/
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}

package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select p from Student p where concat(p.id, '', p.name, '', p.course, '', p.course_id, '', p.clas, '', p.teacher, '', p.score) LIKE %?1%")
    List<Student> search(String keyword);
}

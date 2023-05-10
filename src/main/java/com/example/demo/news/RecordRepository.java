package com.example.demo.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("select p from Record p where concat(p.id, '', p.course, '', p.teacher, '', p.date) LIKE %?1%")
    List<Record> search(String keyword);

}

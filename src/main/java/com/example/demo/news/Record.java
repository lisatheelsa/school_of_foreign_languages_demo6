package com.example.demo.news;

import jakarta.persistence.Entity; // аннотация для указания, что класс является сущностью и относится к ORM JPA
import jakarta.persistence.GeneratedValue; // аннотация для работы со столбцами SQL
import jakarta.persistence.GenerationType; // класс, отвечающий за тип данных перечисления
import jakarta.persistence.Id; // аннотация, отвечающая за определение первичного ключа объекта


@Entity
public class Record {
    private Long id;
    private String course;
    private String teacher;
    private String date;

    public Record() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "record [id=" + id + ", course=" + course + ", text=" + teacher + ", date=" + date + "]";
    }
}


package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    private Long id;
    private String name;
    private String course;
    private String course_id;
    private String clas;
    private String teacher;
    private String score;

    protected Student() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {return course;}
    public void setCourse(String course) {this.course = course;}

    public String getCourse_id() {return course_id;}
    public void setCourse_id(String course_id) {this.course_id = course_id;}

    public String getClas() {return clas;}
    public void setClas(String clas) {this.clas = clas;}

    public String getTeacher() {return teacher;}
    public void setTeacher(String teacher) {this.teacher = teacher;}

    public String getScore() {return score;}
    public void setScore(String score) {this.score = score;}


    @Override
    public String toString() {
        return "student [id=" + id + ", name=" + name + ", course=" + course + ", course_id=" + course_id + ", clas=" + clas + ", teacher=" + teacher + ", score=" + score+ "]";
    }
}

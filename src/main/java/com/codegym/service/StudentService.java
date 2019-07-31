package com.codegym.service;


import com.codegym.model.School;
import com.codegym.model.Student;

public interface StudentService {
    Iterable<Student> findAll();

    Student findById(Integer id);

    void save(Student student);

    void remove(Integer id);

    Iterable<Student> findAllBySchool(School school);
}

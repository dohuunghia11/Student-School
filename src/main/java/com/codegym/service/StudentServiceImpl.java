package com.codegym.service;


import com.codegym.model.School;
import com.codegym.model.Student;
import com.codegym.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepository studentRepository;

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findOne(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Integer id) {
        studentRepository.delete(id);
    }

    @Override
    public Iterable<Student> findAllBySchool(School school) {
        return studentRepository.findAllBySchool(school);
    }
}

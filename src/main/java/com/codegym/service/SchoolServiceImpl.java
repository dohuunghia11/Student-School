package com.codegym.service;

import com.codegym.model.School;
import com.codegym.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SchoolServiceImpl implements SchoolService {

    @Autowired
    public SchoolRepository schoolRepository;

    @Override
    public Iterable<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public School findById(Integer id) {
        return schoolRepository.findOne(id);
    }

    @Override
    public void save(School school) {
        schoolRepository.save(school);
    }

    @Override
    public void remove(Integer id) {
        schoolRepository.delete(id);
    }
}

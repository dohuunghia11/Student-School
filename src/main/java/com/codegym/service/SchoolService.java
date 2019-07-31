package com.codegym.service;

import com.codegym.model.School;

public interface SchoolService {

    Iterable<School> findAll();

    School findById(Integer id);

    void save(School school);

    void remove(Integer id);
}

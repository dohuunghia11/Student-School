package com.codegym.repository;

import com.codegym.model.School;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SchoolRepository extends PagingAndSortingRepository<School, Integer> {

}

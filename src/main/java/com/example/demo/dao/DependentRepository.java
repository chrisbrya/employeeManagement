package com.example.demo.dao;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {

    void deleteDependentById(Long id);

    Optional<Dependent> findDependentById(Long id);
}

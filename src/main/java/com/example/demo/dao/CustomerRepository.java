package com.example.demo.dao;


import com.example.demo.entity.Customer;
import com.example.demo.entity.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    void deleteCustomerById(Long id);

    Optional<Customer> findCustomerById(Long id);
    Customer findCustomerByEmail(String email);

    Customer findCustomerByEmailAndPassword(String email, String password);



}

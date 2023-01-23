package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Dependent;

import java.util.List;

public interface CustomerInterface {

    public Customer saveCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer updateCustomer(Customer customer);

    public Customer findCustomerById(Long id);

    public void deleteCustomer(Long id);

    public void saveDependent(Dependent dependent);

    public List<Dependent> getAllDependents();

    public Dependent updateDependent(Dependent dependent);

    public Dependent findDependentById(Long id);

    public void deleteDependent(Long id);

    public Customer findCustomerByEmail(String email);

    public Customer findCustomerByEmailAndPassword(String email, String password);
}

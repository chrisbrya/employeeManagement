package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DependentRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Dependent;
import com.example.demo.exception.DependentNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerInterfaceImpl implements CustomerInterface{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DependentRepository dependentRepository;


    @Override
    public Customer saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return this.customerRepository.findCustomerById(id).orElseThrow(() -> new UserNotFoundException("User by "+ id + " was not found"));
    }

    @Override
    public void deleteCustomer(Long id) {
        this.customerRepository.deleteCustomerById(id);
    }

    @Override
    public void saveDependent(Dependent dependent) {
        this.dependentRepository.save(dependent);
    }

    @Override
    public List<Dependent> getAllDependents() {
        return this.dependentRepository.findAll();
    }

    @Override
    public Dependent updateDependent(Dependent dependent) {
        return this.dependentRepository.save(dependent);
    }

    @Override
    public Dependent findDependentById(Long id) {
        return this.dependentRepository.findDependentById(id).orElseThrow(() -> new DependentNotFoundException("Dependent by " + id + " was not found"));
    }

    @Override
    public void deleteDependent(Long id) {
        this.dependentRepository.deleteDependentById(id);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return this.customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Customer findCustomerByEmailAndPassword(String email, String password) {
        return this.customerRepository.findCustomerByEmailAndPassword(email, password);
    }

}

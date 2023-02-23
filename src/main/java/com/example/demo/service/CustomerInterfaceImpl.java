package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DependentRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Dependent;
import com.example.demo.exception.DependentNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CustomerInterfaceImpl implements CustomerInterface {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DependentRepository dependentRepository;


    @Override
    public Customer saveCustomer(Customer customer) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String regularPassword = customer.getPassword();
//        String encryptedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
//        customer.setPassword(encryptedPassword);
//        bCryptPasswordEncoder.matches(regularPassword, encryptedPassword);
        this.customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        System.out.println(customer.toString());
        return this.customerRepository.save(customer);

    }

    @Override
    public Customer findCustomerById(Long id) {
        return this.customerRepository.findCustomerById(id).orElseThrow(() -> new UserNotFoundException(id));
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
        return StreamSupport.stream(dependentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Dependent updateDependent(Dependent dependent) {
        return this.dependentRepository.save(dependent);
    }

    @Override
    public Dependent findDependentById(Long id) {
        return this.dependentRepository.findDependentById(id).orElseThrow(() -> new DependentNotFoundException(id));
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

    @Override
    public List<Dependent> findDependentByCustomerEmail(String email) {
        Customer c = this.customerRepository.findCustomerByEmail(email);
        List<Dependent> found = c.getDependent();
        return found;
    }

    @Override
    public Customer loadCustomerByEmail(String email) throws UserNotFoundException {
        Customer currentUser = customerRepository.findCustomerByEmail(email);

        return currentUser;
    }

    @Override
    public String addDependentToCustomer(Dependent dependent) {

        return null;
    }


}

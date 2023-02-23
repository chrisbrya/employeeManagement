package com.example.demo.controller;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Dependent;
import com.example.demo.service.CustomerInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerInterfaceImpl customerInterfaceImpl;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() { return this.customerInterfaceImpl.getAllCustomers(); }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@RequestBody Customer customer) throws Exception {
        String tempEmail = customer.getEmail();
        if (tempEmail != null && !"".equals(tempEmail)) {
           Customer user = customerInterfaceImpl.findCustomerByEmail(tempEmail);
           if (user != null) {
               throw new Exception("User with " +tempEmail + " already exists");
           }
        }
        Customer customer1 = null;
        customer1 = this.customerInterfaceImpl.saveCustomer(customer);
        return customer.toString() + " was processed";
    }

    @GetMapping("/getCustomer/{id}")
    public Customer findCustomerById(@PathVariable("id") Long id) {return this.customerInterfaceImpl.findCustomerById(id);}

    @PostMapping("/login")
    public Customer loginCustomer(@RequestBody Customer customer) throws Exception {

        String tempEmail = customer.getEmail();
        String tempPass = customer.getPassword();
        Customer customer1 = null;
        if(tempEmail != null && tempPass != null) {
           customer1 = customerInterfaceImpl.findCustomerByEmailAndPassword(tempEmail, tempPass);

        }
        if(customer1 == null) {
            throw new Exception("User does not exist");
        }
        return customer1;
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@RequestBody Customer customer) {
        this.customerInterfaceImpl.updateCustomer(customer);
        return customer.toString() + " was updated";
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        this.customerInterfaceImpl.deleteCustomer(id);
        return "Customer was deleted";
    }

    @GetMapping("/getAllDependents")
    public List<Dependent> getAllDependents() { return this.customerInterfaceImpl.getAllDependents(); }

    @PostMapping("/saveDependent")
    public String saveDependent(@RequestBody Dependent dependent) {
        this.customerInterfaceImpl.saveDependent(dependent);
        return dependent.toString() + " was processed";
    }

    @DeleteMapping("/deleteDependent/{id}")
    public String deleteDependent(@PathVariable("id") Long id) {
        this.customerInterfaceImpl.deleteDependent(id);
        return "Dependent was deleted";
    }

    @PostMapping("/updateDependent")
    public String updateDependent(@RequestBody Dependent dependent) {
        this.customerInterfaceImpl.updateDependent(dependent);
        return dependent.toString() + " was updated";
    }

    @GetMapping("/getDependent/{email}")
    public List<Dependent> findDependentsByCustomerEmail(@PathVariable String email) {
        return this.customerInterfaceImpl.findDependentByCustomerEmail(email);
    }

    @GetMapping("/findCustomerByEmail/{email}")
    public Customer findCustomerByEmail(@PathVariable("email") String email) {
        return this.customerInterfaceImpl.findCustomerByEmail(email);
    }


}

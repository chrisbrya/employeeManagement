package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Dependent;
import com.example.demo.service.CustomerInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerInterfaceImpl customerInterfaceImpl;

    @GetMapping("getAllCustomers")
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

    @GetMapping("getAllDependents")
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
}

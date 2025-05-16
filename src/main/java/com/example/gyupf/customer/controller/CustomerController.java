package com.example.gyupf.customer.controller;

import com.example.gyupf.customer.dto.CustomerDto;
import com.example.gyupf.customer.service.CustomerService;
import com.example.gyupf.property.dto.PropertyListDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //test json 형태
    @GetMapping("/test")
    public List<CustomerDto> testQuery() {
        return customerService.getAllCustomers();
    }


    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public void create(@RequestBody CustomerDto dto) {
        customerService.addCustomer(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody CustomerDto dto) {
        dto.setCustomerNum(id);
        customerService.updateCustomer(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}

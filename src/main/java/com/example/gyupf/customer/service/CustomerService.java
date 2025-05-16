package com.example.gyupf.customer.service;

import com.example.gyupf.customer.dto.CustomerDto;
import com.example.gyupf.customer.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public List<CustomerDto> getAllCustomers() {
        return customerMapper.selectAll();
    }

    public CustomerDto getCustomerById(Long customerNum) {
        return customerMapper.selectById(customerNum);
    }

    public void addCustomer(CustomerDto dto) {
        customerMapper.insert(dto);
    }

    public void updateCustomer(CustomerDto dto) {
        customerMapper.update(dto);
    }

    public void deleteCustomer(Long customerNum) {
        customerMapper.delete(customerNum);
    }
}

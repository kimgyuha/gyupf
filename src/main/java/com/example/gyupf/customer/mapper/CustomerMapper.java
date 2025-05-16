package com.example.gyupf.customer.mapper;

import com.example.gyupf.customer.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {

    List<CustomerDto> selectAll();

    CustomerDto selectById(@Param("customerNum") Long customerNum);

    int insert(CustomerDto customer);

    int update(CustomerDto customer);

    int delete(@Param("customerNum") Long customerNum);
}

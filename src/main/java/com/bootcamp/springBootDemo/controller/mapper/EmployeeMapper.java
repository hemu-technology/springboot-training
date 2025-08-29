package com.bootcamp.springBootDemo.controller.mapper;

import com.bootcamp.springBootDemo.controller.dto.EmployeeCreateRequest;
import com.bootcamp.springBootDemo.controller.dto.EmployeeRequest;
import com.bootcamp.springBootDemo.controller.dto.EmployeeResponse;
import com.bootcamp.springBootDemo.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(request, employee);
        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }

    public List<EmployeeResponse> toResponse(List<Employee> employees) {
        return employees.stream().map(this::toResponse).toList();
    }
}

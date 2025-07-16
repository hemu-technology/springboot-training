package com.bootcamp.springBootDemo.controller;

import com.bootcamp.springBootDemo.controller.dto.EmployeeCreateRequest;
import com.bootcamp.springBootDemo.controller.dto.EmployeeResponse;
import com.bootcamp.springBootDemo.controller.dto.EmployeeUpdateRequest;
import com.bootcamp.springBootDemo.controller.mapper.EmployeeMapper;
import com.bootcamp.springBootDemo.model.Employee;
import com.bootcamp.springBootDemo.model.Gender;
import com.bootcamp.springBootDemo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;


    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@RequestBody EmployeeCreateRequest request) {
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeService.saveEmployee(employee));
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.getEmployeeById(id));
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getByGender(@RequestParam("gender") String genderParam) {
        Gender gender = Gender.valueOf(genderParam.toUpperCase());
        return employeeService.getEmployeeByGender(gender);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployee();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Employee request) {
//        try {
//            return ResponseEntity.ok(employeeService.updateEmployee(id, request));
//        } catch (NotFoundException e) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(Map.of(
//                            "message", "The employee has left the company and cannot be modified.",
//                            "error", e.getMessage()
//                    ));
//        }
//    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody EmployeeUpdateRequest request) {
        Employee employee = employeeMapper.toEntity(request);
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public List<EmployeeResponse> getAllByPageSize(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return employeeMapper.toResponse(employeeService.getAllByPageSize(pageNumber, pageSize));
    }
}

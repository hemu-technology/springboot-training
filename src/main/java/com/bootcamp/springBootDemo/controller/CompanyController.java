package com.bootcamp.springBootDemo.controller;

import com.bootcamp.springBootDemo.controller.dto.CompanyRequest;
import com.bootcamp.springBootDemo.controller.dto.CompanyResponse;
import com.bootcamp.springBootDemo.controller.dto.EmployeeResponse;
import com.bootcamp.springBootDemo.controller.mapper.CompanyMapper;
import com.bootcamp.springBootDemo.controller.mapper.EmployeeMapper;
import com.bootcamp.springBootDemo.model.Company;
import com.bootcamp.springBootDemo.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;
    private final EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable Integer id) {
        return companyMapper.toResponse(companyService.getCompanyById(id));
    }

    @GetMapping
    public List<CompanyResponse> getAll() {
        return companyMapper.toResponse(companyService.getAllCompany());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        Company company = companyMapper.toEntity(request);
        return companyMapper.toResponse(companyService.saveCompany(company));
    }

    @PutMapping("/{id}")
    public CompanyResponse update(@PathVariable Integer id, @RequestBody CompanyRequest request) {
        Company company = companyMapper.toEntity(request);
        return companyMapper.toResponse(companyService.updateCompany(id, company));
    }

    @DeleteMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        companyService.deleteCompanyById(id);
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> getEmployeesById(@PathVariable Integer id) {
        return employeeMapper.toResponse(companyService.getEmployeesById(id));
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public List<CompanyResponse> getAllByPageSize(@RequestParam Integer pageNumber,
                                                  @RequestParam Integer pageSize) {
        return companyMapper.toResponse(companyService.getAllByPageSize(pageNumber, pageSize));
    }
}

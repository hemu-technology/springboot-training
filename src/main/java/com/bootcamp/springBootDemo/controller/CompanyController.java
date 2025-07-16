package com.bootcamp.springBootDemo.controller;

import com.bootcamp.springBootDemo.model.Company;
import com.bootcamp.springBootDemo.model.Employee;
import com.bootcamp.springBootDemo.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/{id}")
    public Company getById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping
    public List<Company> getAll() {
        return companyService.getAllCompany();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company request) {
        return companyService.saveCompany(request);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Integer id, @RequestBody Company request) {
        return companyService.updateCompany(id, request);
    }

    @DeleteMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        companyService.deleteCompanyById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesById(@PathVariable Integer id) {
        return companyService.getEmployeesById(id);
    }

    @GetMapping(params = {"pageNumber", "pageSize"})
    public List<Company> getAllByPageSize(@RequestParam Integer pageNumber,
                                          @RequestParam Integer pageSize) {
        return companyService.getAllByPageSize(pageNumber, pageSize);
    }
}

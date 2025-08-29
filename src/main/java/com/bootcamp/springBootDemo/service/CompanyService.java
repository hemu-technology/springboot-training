package com.bootcamp.springBootDemo.service;

import com.bootcamp.springBootDemo.model.Company;
import com.bootcamp.springBootDemo.model.Employee;
import com.bootcamp.springBootDemo.repository.CompanyDBRepository;
import com.bootcamp.springBootDemo.repository.CompanyRepository;
import com.bootcamp.springBootDemo.repository.EmployeeDBRepository;
import com.bootcamp.springBootDemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private  final EmployeeRepository employeeRepository;

    public CompanyService(CompanyDBRepository companyDBRepository, EmployeeDBRepository employeeRepository) {
        this.companyRepository = companyDBRepository;
        this.employeeRepository = employeeRepository;
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.getById(id);
    }

    public List<Company> getAllCompany() {
        return companyRepository.getAll();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer id, Company company) {
        Company preCompany = companyRepository.getById(id);
        if (preCompany == null) {
            return null;
        }
        String name = company.getName() == null ? preCompany.getName() : company.getName();
        Company newCompany = new Company(id, name);
        return companyRepository.update(newCompany);
    }

    public void deleteCompanyById(Integer id) {
        companyRepository.delete(id);
    }

    public List<Employee> getEmployeesById(Integer id) {
        return employeeRepository.getEmployeesByCompanyId(id);
    }

    public List<Company> getAllByPageSize(Integer pageNumber, Integer pageSize) {
        return companyRepository.getAllByPageSize(pageNumber, pageSize);
    }
}

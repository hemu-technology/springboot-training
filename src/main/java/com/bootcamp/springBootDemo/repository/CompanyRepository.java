package com.bootcamp.springBootDemo.repository;

import com.bootcamp.springBootDemo.model.Company;
import com.bootcamp.springBootDemo.model.Employee;
import com.bootcamp.springBootDemo.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CompanyRepository {

    private final List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        initData();
    }

    private void initData() {
        this.companies.add(new Company(1, "Acme Corporation", List.of(
                new Employee(1, "John Smith", 32, Gender.MALE, 5000.0),
                new Employee(2, "Jane Johnson", 28, Gender.FEMALE, 6000.0))));
        this.companies.add(new Company(2, "TechCom Solutions", List.of(
                new Employee(3, "David Williams", 35, Gender.MALE, 5500.0),
                new Employee(4, "Emily Brown", 23, Gender.FEMALE, 4500.0),
                new Employee(5, "Michael Jones", 40, Gender.MALE, 7000.0))));
        this.companies.add(new Company(3, "Global Innovators", List.of()));
        this.companies.add(new Company(4, "Stellar Enterprises", List.of()));
        this.companies.add(new Company(5, "Nexus Industries", List.of()));
    }

    public Company save(Company company) {
        Company newCompany = new Company(this.companies.size() + 1, company.getName(), company.getEmployees());
        companies.add(newCompany);
        return newCompany;
    }

    public Company getById(Integer id) {
        return companies.stream().filter(company -> company.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Company> getAll() {
        return companies;
    }

    public Company update(Company newCompany) {
        return companies.stream()
                .filter(company -> company.getId().equals(newCompany.getId()))
                .findFirst()
                .map(company -> {
                    if (newCompany.getName() != null) {
                        company.setName(newCompany.getName());
                    }
                    return company;
                })
                .orElse(null);
    }

    public void delete(Integer id) {
        companies.removeIf(company -> company.getId().equals(id));
    }

    public List<Employee> getEmployeesById(Integer id) {
        return companies.stream().filter(company -> company.getId().equals(id))
                .map(Company::getEmployees)
                .findFirst()
                .orElse(Collections.emptyList());
    }

    public List<Company> getAllByPageSize(Integer pageNumber, Integer pageSize) {
        return companies.stream().skip((long) (pageNumber - 1) * pageSize).limit(pageSize).toList();
    }
}

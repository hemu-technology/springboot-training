package com.bootcamp.springBootDemo.controller.mapper;

import com.bootcamp.springBootDemo.controller.dto.CompanyRequest;
import com.bootcamp.springBootDemo.controller.dto.CompanyResponse;
import com.bootcamp.springBootDemo.model.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequest request) {
        Company company = new Company();
        BeanUtils.copyProperties(request, company);
        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        return companyResponse;
    }

    public List<CompanyResponse> toResponse(List<Company> companies) {
        return companies.stream().map(this::toResponse).toList();
    }
}

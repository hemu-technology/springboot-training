package com.bootcamp.springBootDemo.controller.dto;

import com.bootcamp.springBootDemo.model.Gender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeCreateRequest extends EmployeeRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 65, message = "Age must be no more than 65")
    private Integer age;

    @NotNull
    private Gender gender;

    private Double salary;

    private Integer companyId;

    public EmployeeCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}

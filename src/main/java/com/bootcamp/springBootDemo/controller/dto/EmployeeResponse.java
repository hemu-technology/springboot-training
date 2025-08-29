package com.bootcamp.springBootDemo.controller.dto;

import com.bootcamp.springBootDemo.model.Gender;

public class EmployeeResponse {
    private Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    //    private Double salary; // it's sensitive data, should not pass to client
    private Integer companyId;

    public EmployeeResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}

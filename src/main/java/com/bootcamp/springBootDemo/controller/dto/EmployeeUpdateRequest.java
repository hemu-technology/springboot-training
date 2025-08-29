package com.bootcamp.springBootDemo.controller.dto;

import com.bootcamp.springBootDemo.model.Gender;

public class EmployeeUpdateRequest extends EmployeeRequest {
    private String name;

    private Integer age;

    private Gender gender;

    public EmployeeUpdateRequest() {
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
}

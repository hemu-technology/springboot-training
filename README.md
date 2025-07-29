# Spring Boot Employee API Error Handling - Teaching Outline

## 🎯 Goal

Teach students how to handle exceptions in a Spring Boot application using both **local try-catch** and **global exception handler** with `@RestControllerAdvice`.

---

## ✅ Step 1: Handle the scenario when the employee does not exist during update

```java
public Employee updateEmployee(Integer id, Employee request) {
    Employee preEmployee = employeeRepository.getById(id);
    if (preEmployee == null) {
        throw new NotFoundException("Employee with id " + id + " not found.");
    }
    if (!preEmployee.getActive()) {
        throw new InactiveEmployeeException("Cannot update inactive employee.");
    }

    String name = request.getName() == null ? preEmployee.getName() : request.getName();
    Integer age = request.getAge() == null ? preEmployee.getAge() : request.getAge();
    Gender gender = request.getGender() == null ? preEmployee.getGender() : request.getGender();
    Double salary = request.getSalary() == null ? preEmployee.getSalary() : request.getSalary();
    Employee newEmployee = new Employee(id, name, age, gender, salary);
    return employeeRepository.update(newEmployee);
    
    // unit test

    @Test
    void should_throw_exception_when_updating_an_non_exist_employee() {
        // given
        Employee employee = new Employee(1, "non-exist", 40, Gender.FEMALE, 26000.0);
        employee.setActive(false);
        when(employeeRepository.getById(1)).thenReturn(employee);

        Employee update = new Employee(null, "NewName", 40, Gender.FEMALE, 26000.0);

        // when
        // then
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> employeeService.updateEmployee(2, update));
        assertEquals("Employee with id " + 2 + " not found.", ex.getMessage());
    }
}
```

### 📚 Explanation:

* `throw new NotFoundException(...)`: Triggers a business-specific exception when employee does not exist.
* `throw new InactiveEmployeeException(...)`: Additional rule: inactive employees cannot be updated.

---

## ✅ Step 2: Local try-catch handling in Controller

```java
@PutMapping("/{id}")
public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Employee request) {
    try {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    } catch (NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", "The employee has left the company and cannot be modified.",
                        "error", e.getMessage()
                ));
    }
}
```

### 📚 Explanation:

* `@PutMapping`: Binds HTTP PUT request to this method.
* `@PathVariable`: Injects the employee ID from the URL.
* `@RequestBody`: Maps request JSON to an Employee object.
* `ResponseEntity`: A Spring helper to control status and body in the HTTP response.

---

## ✅ Step 3: Global Exception Handling with @RestControllerAdvice

```java
package com.bootcamp.springBootDemo.advice;

import com.bootcamp.springBootDemo.exception.InactiveEmployeeException;
import com.bootcamp.springBootDemo.exception.InvalidEmployeeException;
import com.bootcamp.springBootDemo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", "Resource not found.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    @ExceptionHandler(InactiveEmployeeException.class)
    public ResponseEntity<?> handleInactiveEmployeeException(InactiveEmployeeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", "The employee has left the company and cannot be modified.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    public ResponseEntity<?> handleInvalidEmployeeException(InvalidEmployeeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", "The employee information is invalid.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message", "Internal system error.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}
```

### 📚 Explanation of Annotations:

* `@RestControllerAdvice`: Indicates this class will globally handle exceptions thrown by any controller.
* `@ExceptionHandler(XxxException.class)`: Handles exceptions of specific types and maps them to HTTP responses.
* `ResponseEntity`: Customizes HTTP status and response body.

---

## 🧠 Suggested Class Activities:

1. Globally handle **InactiveEmployeeException** and **InvalidEmployeeException**

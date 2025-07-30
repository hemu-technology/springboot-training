# Spring Boot Demo - DTO & Mapping Layer Isolation

## 🎯 Objective

In this module, we demonstrate how to **hide sensitive data** and **decouple internal entity structure** using **DTO (
Data Transfer Object)** and **Mapper** classes.

---

## ✅ Requirements

### 📌 Hiding `salary` in response

1. **Create** `EmployeeResponse` (exclude salary)
2. **Create** `EmployeeMapper.toResponse(Employee)`
3. **Refactor** `EmployeeService` usages in:

    * `create`
    * `update`
    * `getById`
    * `getAll`

### 📌 Remove `id` from request

1. **Create** `EmployeeCreateRequest` and `EmployeeUpdateRequest` (no ID field)
2. **Map** to entity using `EmployeeMapper.toEntity(EmployeeRequest)`
3. **Refactor** `EmployeeService` usages in:

    * `create`
    * `update`

### 📌 Add libs for validation

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## 🔄 DTO Classes

### `EmployeeCreateRequest.java`

```java
public class EmployeeCreateRequest extends EmployeeRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Min(value = 18)
    @Max(value = 65)
    private Integer age;

    @NotNull
    private Gender gender;

    private Double salary;
    private Integer companyId;
    // Getters & Setters
}
```

### `EmployeeUpdateRequest.java`

```java
public class EmployeeUpdateRequest extends EmployeeRequest {
    private String name;
    private Integer age;
    private Gender gender;
    // Getters & Setters
}
```

### `EmployeeResponse.java`

```java
public class EmployeeResponse {
    private Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private Integer companyId;
    // Getters & Setters
}
```

---

## 🔄 Mapper Class

### `EmployeeMapper.java`

```java

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(request, employee);
        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        BeanUtils.copyProperties(employee, response);
        return response;
    }

    public List<EmployeeResponse> toResponse(List<Employee> employees) {
        return employees.stream().map(this::toResponse).toList();
    }
}
```

---

## 💻 Controller Usage

### `EmployeeController.java`

```java

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public EmployeeResponse create(@Valid @RequestBody EmployeeCreateRequest request) {
    Employee employee = employeeMapper.toEntity(request);
    return employeeMapper.toResponse(employeeService.saveEmployee(employee));
}

@GetMapping("/{id}")
public EmployeeResponse getById(@PathVariable Integer id) {
    return employeeMapper.toResponse(employeeService.getEmployeeById(id));
}

@GetMapping(params = {"pageNumber", "pageSize"})
public List<EmployeeResponse> getAllByPageSize(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
    return employeeMapper.toResponse(employeeService.getAllByPageSize(pageNumber, pageSize));
}

@PutMapping("/{id}")
public Employee update(@PathVariable Integer id, @RequestBody EmployeeUpdateRequest request) {
    Employee employee = employeeMapper.toEntity(request);
    return employeeService.updateEmployee(id, employee);
}
```

### `GlobalExceptionHandler.java`

```java

package com.bootcamp.springBootDemo.advice;

import com.bootcamp.springBootDemo.exception.InactiveEmployeeException;
import com.bootcamp.springBootDemo.exception.InvalidEmployeeException;
import com.bootcamp.springBootDemo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        response.put("message", "Validation failed");
        response.put("errors", fieldErrors);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
```

---

## 🧪 Suggested Exercises (For Class)

* 🔨 Add custom mapping logic in `EmployeeMapper` (e.g., handle default companyId)
* ❌ Throw validation error when age is below 18 or missing gender
* 🧪 Write integration tests using DTO

---

## 💡 Key Takeaways

* **DTOs** protect internal model exposure
* **Mappers** decouple domain from presentation layer
* This pattern improves **security**, **flexibility**, and **testability**

# TDD
### Requirements
1. Employees those who are not 18 ~ 65 years old cannot be created.
2. Employees over 30 years of age (inclusive) with a salary below 20000 cannot be created.
3. Creating a new employee will set the employee's active status to true by default.
4. Deleting an employee simply sets the employee's active status to false.
5. When updating an employee, you need to verify whether the employee is active or not, if he/she has already left the company, you can't update him/her.


### Steps
1. tasking

   | Task ID | Description                                                                                                       |
   | ------- |-------------------------------------------------------------------------------------------------------------------|
   | T1      | Employee must be between 18 and 65 years old (inclusive); otherwise, creation should be rejected.                 |
   | T2      | Employees aged 30 or above must have a salary ≥ 20000; otherwise, creation should be rejected.                    |
   | T3      | New employees must have their `active` status set to `true` by default upon creation.                             |
   | T4      | Deleting an employee should only mark them as inactive (`active = false`) instead of removing them from the list. |
   | T5      | Updating an employee should only be allowed if they are active; updating an inactive employee should return error |
   | T6      | Active employees should be updatable, and the updated values must be correctly saved.                             |

2. update Employee class to add `active` field
``` java
private Boolean active = true;
```
please remember to add getter/setter。

2. add unit test using junit5
> Use @ExtendWith(SpringExtension.class) that means please using Spring to run the test class, like auto wire bean and load spring config
> Use @InjectMocks is a Mockito annotation used in unit testing to create an instance of the class under test and automatically inject mock dependencies into it.



package skypro.hw2_7.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import skypro.hw2_7.Employee;
import skypro.hw2_7.exception.BadRequestException;
import skypro.hw2_7.exception.EmployeeAlreadyAddedException;
import skypro.hw2_7.exception.EmployeeNotFoundException;


import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    public Employee add(String contractNumber, String firstName, String lastName) {

        checkNames(firstName, lastName);

        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.containsValue(newEmployee) || employees.containsKey(contractNumber)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в базе");
        }
        employees.put(contractNumber, newEmployee);
        return newEmployee;
    }

    public void checkCapitalNames(String firstName, String lastName) {
        String capitalFirstName = StringUtils.capitalize(firstName);
        String capitalLastName = StringUtils.capitalize(lastName);

        if (!firstName.equals(capitalFirstName)) {
            throw new BadRequestException("Введенное имя не с заглавной буквы");
        }
        if (!lastName.equals(capitalLastName)) {
            throw new BadRequestException("Введенная фамилия не с заглавной буквы");
        }
    }

    public void checkSymbols(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new BadRequestException("В введенном имени содержатся запрещенные символы");
        }
        if (!StringUtils.isAlpha(lastName)) {
            throw new BadRequestException("В введенной фамилии содержатся запрещенные символы");
        }
    }

    public void checkNames(String firstName, String lastName) {
        checkCapitalNames(firstName, lastName);
        checkSymbols(firstName, lastName);
    }

    public Employee remove(String contractNumber, String firstName, String lastName) {
        Employee EmployeeToRemove = new Employee(firstName, lastName);
        if (employees.containsValue(EmployeeToRemove)) {
            return employees.remove(contractNumber);
        }
        else throw new EmployeeNotFoundException("Сотрудник не удален,так как не найден");
    }

    public Employee find(String contractNumber, String firstName, String lastName) {
        Employee employeeToFind = new Employee(firstName, lastName);
        if (employees.containsValue(employeeToFind)) {
            return employees.get(contractNumber);
            }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    public Map<String, Employee> getAll() {
        return employees;
    }
}


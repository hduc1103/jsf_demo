package repository;

import java.util.List;

import model.Employee;

public interface EmployeeDAO {
	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(int id);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int id);
}

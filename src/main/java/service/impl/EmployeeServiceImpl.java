package service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Employee;
import dao.EmployeeDAO;
import service.EmployeeService;

@Named("employeeService")
@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {
	
	@Inject
    private EmployeeDAO employeeDAO;
	
	@Override
	public void addEmployee(Employee employee) {
		employee.setAge(Period.between(employee.getDob(), LocalDate.now()).getYears());
		employeeDAO.addEmployee(employee);	
	}

	@Override
	public void deleteEmployee(String code) {
		employeeDAO.deleteEmployee(code);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public void updateEmployee(Employee newEmployeeData) {
		newEmployeeData.setAge(Period.between(newEmployeeData.getDob(), LocalDate.now()).getYears());
		employeeDAO.updateEmployee(newEmployeeData);
	}

	@Override
	public Employee searchByCode(String code) {
		Employee employee = employeeDAO.getEmployeeByCode(code);
		return employee;
	}

}
 
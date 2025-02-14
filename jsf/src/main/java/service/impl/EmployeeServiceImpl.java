package service.impl;

import java.util.List;

import javax.inject.Inject;

import model.Employee;
import repository.EmployeeDAO;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	
	@Inject
    private EmployeeDAO employeeDAO;
	
	@Override
	public void addEmployee(Employee employee) {
		
		employeeDAO.addEmployee(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
 
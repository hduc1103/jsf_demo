package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.EmployeeException;
import model.Employee;
import dao.EmployeeDAO;
import util.DatabaseUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	@Override
	public void addEmployee(Employee employee) {
		if (getEmployeeByCode(employee.getCode()) != null) {
			throw new EmployeeException("Employee code already exits");
		} else if (employee.getAge() < 1) {
			throw new EmployeeException("Age must be >0");
		}

		String query = "INSERT INTO Mt_employee (employee_code, employee_name, employee_age, date_of_birth) VALUES (?, ?, ?, ?)";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, employee.getCode());
			stmt.setString(2, employee.getName());
			stmt.setInt(3, employee.getAge());
			stmt.setDate(4, new java.sql.Date(employee.getDob().getTime()));

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee with code, name " + employee.getCode() + " " + employee.getName()
						+ " was added successfully!");
			} else {
				throw new EmployeeException("Failed to add employee " + employee.getName());
			}

		} catch (SQLException e) {
			throw new EmployeeException("Database error while adding employee: " + e.getMessage(), e);
		}
	}

	@Override
	public void updateEmployee(Employee newEmployeeData) {
		String query = "UPDATE Mt_employee SET employee_name = ?, employee_age = ?, date_of_birth = ? WHERE employee_code = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, newEmployeeData.getName());
			stmt.setInt(2, newEmployeeData.getAge());
			stmt.setDate(3, new java.sql.Date(newEmployeeData.getDob().getTime()));
			stmt.setString(4, newEmployeeData.getCode());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee with code, name" + newEmployeeData.getCode() + ", "
						+ newEmployeeData.getName() + " was updated successfully!");
			} else {
				throw new EmployeeException("Failed to update employee with code " + newEmployeeData.getCode());
			}

		} catch (SQLException e) {
			throw new EmployeeException("Database error while updating employee: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteEmployee(String code) {
		String query = "DELETE FROM Mt_employee WHERE employee_code = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, code);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee with code " + code + " was successfully deleted");
			} else {
				throw new EmployeeException("Failed to delete employee " + code);
			}
		} catch (Exception e) {
			throw new EmployeeException("Database error while deleting employee: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		String query = "SELECT * FROM Mt_employee";

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				employeeList.add(new Employee(rs.getString("employee_code"), rs.getString("employee_name"),
						rs.getInt("employee_age"), rs.getDate("date_of_birth")));
			}

		} catch (Exception e) {
			throw new EmployeeException("Database error while fetching employee: " + e.getMessage(), e);
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeByCode(String code) {
		String query = "SELECT * FROM Mt_employee WHERE employee_code = ?";
		Employee employee = null;

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, code);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					employee = new Employee();
					employee.setCode(code);
					employee.setAge(rs.getInt("employee_age"));
					employee.setName(rs.getString("employee_name"));
					employee.setDob(rs.getDate("date_of_birth"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

}

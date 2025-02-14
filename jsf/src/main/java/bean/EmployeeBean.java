package bean;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import util.DatabaseUtil;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {
    private List<Employee> employees;
    private Employee employee = new Employee(); // For form input

    // Load employees when the bean is created
    @PostConstruct
    public void init() {
        employees = getAllEmployees();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> empList = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Mt_employee");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                empList.add(new Employee(
                        rs.getInt("employee_code"),
                        rs.getString("employee_name"),
                        rs.getInt("employee_age"),
                        rs.getDate("date_of_birth").toLocalDate()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

    public String addEmployee() {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Mt_employee (employee_code, employee_name, employee_age, date_of_birth) VALUES (?, ?, ?, ?)")) {

            stmt.setInt(1, employee.getCode());
            stmt.setString(2, employee.getName());
            stmt.setInt(3, employee.getAge());
            stmt.setDate(4, java.sql.Date.valueOf(employee.getDob()));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee added successfully!");
                employees.add(new Employee(employee.getCode(), employee.getName(), employee.getAge(), employee.getDob()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "employeeList"; // Navigate back to the list page
    }
}

package bean;

import javax.inject.Inject;
import javax.inject.Named;

import exception.EmployeeException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;
import model.Employee;
import service.EmployeeService;

@Named("employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Employee> employees;
    private Employee newEmployee;
    
    @Inject
    private EmployeeService employeeService;

    @PostConstruct
    public void init() {
        employees = employeeService.getAllEmployees();
        newEmployee = new Employee(); 
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }
 
    public void addEmployee() {
        try {
            employeeService.addEmployee(newEmployee);
            employees = employeeService.getAllEmployees(); 
            newEmployee = new Employee(); 
        } catch (EmployeeException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }

    public void deleteEmployee(String code) { 
        employeeService.deleteEmployee(code);
        employees = employeeService.getAllEmployees();
    }
    
    public String updateEmployee() {
//    	employeeService.updateEmployee(newEmployeeData);
    	return "update";
    }
    
}

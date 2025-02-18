package bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.EmployeeException;
import model.Employee;
import service.EmployeeService;

@Named("updateEmployeeBean")
@RequestScoped
public class UpdateEmployeeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Employee selectedEmployee;
	
	@Inject 
	EmployeeService employeeService;
	
	public Employee getSelectedEmployee(){
		return selectedEmployee;
	}
	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	
	public String updateEmployee() {
		try {
		employeeService.updateEmployee(selectedEmployee);
		return "index?faces-redirect=true";
		}catch(EmployeeException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return null;		
	}
}

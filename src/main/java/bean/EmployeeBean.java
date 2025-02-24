package bean;

import javax.inject.Inject;
import javax.inject.Named;

import exception.EmployeeException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.EmployeeComparators;
import service.EmployeeService;

@Named("employeeBean")
@ViewScoped
public class EmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmployeeService employeeService;

	private List<Employee> employees;
	private Employee newEmployee;
	private Employee curEmployee;

	public List<Employee> getEmployees() {
		return employees;
	}

	public Employee getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(Employee newEmployee) {
		this.newEmployee = newEmployee;
	}

	public Employee getCurEmployee() {
		return curEmployee;
	}

	public void setCurEmployee(Employee curEmployee) {
		this.curEmployee = curEmployee;
	}

	private boolean showAdd = false;
	private boolean showUpdate = false;
	private boolean showList = true;

	public boolean isShowAdd() {
		return showAdd;
	}

	public void setShowAdd(boolean showAdd) {
		this.showAdd = showAdd;
	}

	public boolean isShowUpdate() {
		return showUpdate;
	}

	public void setShowUpdate(boolean showUpdate) {
		this.showUpdate = showUpdate;
	}

	public boolean isShowList() {
		return showList;
	}

	public void setShowList(boolean showList) {
		this.showList = showList;
	}

	@PostConstruct
	public void init() {
		employees = employeeService.getAllEmployees();
		newEmployee = new Employee();
	}

	public void refresh() {
		employees = employeeService.getAllEmployees();
	}

	public void toggleAdd() {
		showAdd = true;
		showList = false;
	}

	public void cancelAdd() {
		showAdd = false;
		showList = true;
	}

	public void toggleUpdate(Employee employee) {
		curEmployee = employee;
		showUpdate = true;
		showList = false;
	}

	public void cancelUpdate() {
		showUpdate = false;
		showList = true;
	}

	public void addEmployee() {
		try {
			employeeService.addEmployee(newEmployee);
			employees = employeeService.getAllEmployees();
			refresh();
			showAdd = false;
			showList = true;
		} catch (EmployeeException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}

	public void deleteEmployee(String code) {
		employeeService.deleteEmployee(code);
		employees = employeeService.getAllEmployees();
	}

	public void updateEmployee() {
		employeeService.updateEmployee(curEmployee);
		refresh();
		curEmployee = new Employee();
		showUpdate = false;
		showList = true;
	}

	public void sortByName() {
		employees.sort(EmployeeComparators.byName);
		employees = new ArrayList<>(employees);
	}

	public void sortByAge() {
		employees.sort(EmployeeComparators.byAge);
		employees = new ArrayList<>(employees);
	}

	public void sortByDob() {
		employees.sort(new EmployeeComparators.byDoB());
		employees = new ArrayList<>(employees);
	}
}

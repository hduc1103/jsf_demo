package model;

import java.time.LocalDate;

public class Employee {
	private String code;
	private String name;
	private int age;
	private LocalDate dob;

	public Employee(String code, String name, int age, LocalDate dob) {
		super();
		this.code = code;
		this.name = name;
		this.age = age;
		this.dob = dob;
	}

	public Employee() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

}

package org.com.cay.service;

import org.com.cay.entity.Employee;
import org.springframework.data.domain.Page;

public interface IEmployeeService {

	public Page<Employee> getPage(int pageNo, int pageSize);
	
	public void save(Employee employee);
	
	public Employee getByLastName(String lastName);
	
	public Employee getById(Integer id);
	
	public void delete(Integer id);
}

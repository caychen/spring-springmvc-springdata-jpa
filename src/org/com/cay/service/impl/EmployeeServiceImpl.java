package org.com.cay.service.impl;

import java.util.Date;

import org.com.cay.entity.Employee;
import org.com.cay.repository.EmployeeRepository;
import org.com.cay.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
		
	public Page<Employee> getPage(int pageNo, int pageSize){
		Pageable pageable = new PageRequest(pageNo - 1, pageSize);
		return employeeRepository.findAll(pageable);
	}

	@Transactional(readOnly=false)
	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		if(employee.getId() == null){			
			employee.setCreateTime(new Date());
		}
		employeeRepository.saveAndFlush(employee);
	}

	@Override
	public Employee getByLastName(String lastName) {
		// TODO Auto-generated method stub
		return employeeRepository.getByLastName(lastName);
	}

	@Override
	public Employee getById(Integer id) {
		// TODO Auto-generated method stub
		return employeeRepository.findOne(id);
	}

	@Transactional(readOnly=false)
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		employeeRepository.delete(id);
	}
	
	
}

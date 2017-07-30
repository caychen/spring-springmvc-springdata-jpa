package org.com.cay.service.impl;

import java.util.List;

import org.com.cay.entity.Department;
import org.com.cay.repository.DepartmentRepository;
import org.com.cay.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentRepository.getAll();
	}
	
	
}

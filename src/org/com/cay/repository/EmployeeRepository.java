package org.com.cay.repository;

import org.com.cay.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee getByLastName(String lastName);
}

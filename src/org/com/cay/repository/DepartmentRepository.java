package org.com.cay.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.com.cay.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@QueryHints(value={@QueryHint(name=org.hibernate.jpa.QueryHints.HINT_CACHEABLE,value="true")})
	@Query("from Department d")
	public List<Department> getAll();
	
}

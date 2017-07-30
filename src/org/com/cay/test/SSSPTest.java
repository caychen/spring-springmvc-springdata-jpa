package org.com.cay.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.com.cay.entity.Department;
import org.com.cay.repository.DepartmentRepository;
import org.hibernate.jpa.QueryHints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class SSSPTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testDataSource(){
		System.out.println(dataSource);
	}
	
	@Test
	public void testRepositorySecondLevelCache(){
		List<Department> departments = departmentRepository.getAll();
		departments = departmentRepository.getAll();
	}
	
	@Test
	public void testJpaSecondLevelCache(){
		String jpql = "from Department d";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Department> departments = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		departments = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		entityManager.close();
		
	}
}

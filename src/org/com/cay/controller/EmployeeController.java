package org.com.cay.controller;

import java.util.Map;

import org.com.cay.entity.Employee;
import org.com.cay.service.IDepartmentService;
import org.com.cay.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDepartmentService departmentService;

	@RequestMapping("/emps")
	public String list(@RequestParam(name = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			pageNo = pageNo < 1 ? 1 : pageNo;
		} catch (Exception e) {

		}

		Page<Employee> page = employeeService.getPage(pageNo, 5);
		map.put("page", page);
		return "emp/list";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(Employee employee) {
		employeeService.save(employee);
		return "redirect:/emps";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("departments", departmentService.getAll());
		map.put("employee", new Employee());
		return "emp/input";
	}

	@ResponseBody
	@RequestMapping(value = "/validateLastName", method = RequestMethod.POST)
	public String validateLastName(@RequestParam(value = "lastName", required = true) String lastName) {
		Employee employee = employeeService.getByLastName(lastName);
		if (employee == null) {
			return "0";
		}
		return "1";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		Employee employee = employeeService.getById(id);
		map.put("employee", employee);
		map.put("departments", departmentService.getAll());
		return "emp/input";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
	public String update(Employee employee) {
		employeeService.save(employee);
		return "redirect:/emps";
	}

	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {// 修改
			Employee employee = employeeService.getById(id);
			
			//把关联的department对象置为null
			employee.setDepartment(null);
			map.put("employee", employee);
		}
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id){
		employeeService.delete(id);
		return "redirect:/emps";
	}
}

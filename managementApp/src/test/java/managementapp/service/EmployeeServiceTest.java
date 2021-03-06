package managementapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import managementapp.dto.EmployeeDTO;
import managementapp.exceptions.BusinessException;
import managementapp.model.Employee;
import managementapp.model.Job;
import managementapp.repository.EmployeeRepository;
import managementapp.service.impl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

	private static EmployeeRepository employeeRepository = mock(EmployeeRepository.class);

	@TestConfiguration
	static class ContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl(employeeRepository);
		}
	}

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void testFindByJob() throws BusinessException {
		Employee emp=new Employee("John", "Doe", Job.VENDOR);
		Collection<Employee> employees = new ArrayList<Employee>();
		employees.add(emp);
		Mockito.when(employeeRepository.findByJob(ArgumentMatchers.anyString())).thenReturn(employees);
		List<EmployeeDTO> results = new ArrayList<EmployeeDTO>(employeeService.findByJob("VENDOR"));
		assertEquals(results.size(), 1);
		assertTrue(results.get(0).getFirstName().equals(emp.getFirstName()));
		assertTrue(results.get(0).getLastName().equals(emp.getLastName()));
		assertTrue(results.get(0).getJob().equals(emp.getJob()));
	}

}

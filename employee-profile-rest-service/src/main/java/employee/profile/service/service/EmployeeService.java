package employee.profile.service.service;

import org.springframework.http.ResponseEntity;

import employee.profile.service.request.EmployeeCreateReq;
import employee.profile.service.response.Response;

public interface EmployeeService {
	
	ResponseEntity<Response> createEmployee(EmployeeCreateReq request);
	
	
	ResponseEntity<Response> fetchEmployeeRecords();
	
	ResponseEntity<Response> fetchEmployeeRecord(Long id);
	
	ResponseEntity<Response> updateEmployeeRecord(Long id);
	
	ResponseEntity<Response> deleteEmployeeRecord(Long id);
}

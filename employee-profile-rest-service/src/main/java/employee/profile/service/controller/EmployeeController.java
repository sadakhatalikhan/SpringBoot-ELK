package employee.profile.service.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import employee.profile.service.request.EmployeeCreateReq;
import employee.profile.service.response.Response;
import employee.profile.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	
	private final EmployeeService employeeService;
	
	// create Method with postmapping
	@PostMapping("/create")
	public ResponseEntity<Response> createEmployee(@Valid @RequestBody EmployeeCreateReq request){
		return employeeService.createEmployee(request);
	}
	
	@GetMapping("/getAllEmps")
	public ResponseEntity<Response> fetchAllEmployeeRecords() {
		return employeeService.fetchEmployeeRecords();
	}
	
	@GetMapping("/getEmp/{id}")
	public ResponseEntity<Response> fetchEmployeeRecord(@PathVariable Long id) {
		return employeeService.fetchEmployeeRecord(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updateEmployeeRecord(@PathVariable Long id){
		return employeeService.updateEmployeeRecord(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteEmployeeRecord(@PathVariable Long id){
		return employeeService.deleteEmployeeRecord(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return errors;
	}
	
}

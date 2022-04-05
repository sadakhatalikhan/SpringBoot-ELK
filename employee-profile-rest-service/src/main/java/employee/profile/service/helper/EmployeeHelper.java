package employee.profile.service.helper;

import org.springframework.stereotype.Component;

import employee.profile.service.dto.EmployeeDTO;
import employee.profile.service.model.EmployeeModel;
import employee.profile.service.request.EmployeeCreateReq;

@Component
public class EmployeeHelper {
	
	public EmployeeModel modelMapper(EmployeeCreateReq req) {
		return EmployeeModel.builder()
					.employeeName(req.getEmployeeName())
					.employeeAddress(req.getEmployeeAddress())
					.employeeAge(req.getEmployeeAge())
					.yearsOfExp(req.getYearsOfExp())
					.build();
	}
	
	
	public EmployeeDTO dtoMapper(EmployeeModel emp) {
		return EmployeeDTO.builder()
				.id(emp.getId())
				.employeeName(emp.getEmployeeName())
				.employeeAddress(emp.getEmployeeAddress())
				.employeeAge(emp.getEmployeeAge())
				.yearsOfExp(emp.getYearsOfExp())
				.build();
	}
	
}

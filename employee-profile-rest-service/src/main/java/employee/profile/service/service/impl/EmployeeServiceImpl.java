package employee.profile.service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import employee.profile.service.helper.EmployeeHelper;
import employee.profile.service.model.EmployeeModel;
import employee.profile.service.repository.EmployeeRespository;
import employee.profile.service.request.EmployeeCreateReq;
import employee.profile.service.response.Response;
import employee.profile.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRespository employeeRespository;
	private final EmployeeHelper employeeHelper;
	
	@Override
	public ResponseEntity<Response> createEmployee(EmployeeCreateReq request) {
		
		 Optional<EmployeeModel> empModelObj =  employeeRespository.findByEmployeeName(request.getEmployeeName());
		 
		 if(empModelObj.isPresent()) {
			 EmployeeModel model = empModelObj.get();
			 return ResponseEntity.ok().body(
						Response.builder()
							.code(999)
							.message("Employee already exists in the database")
							.body(employeeHelper.dtoMapper(model))
							.build());
		 } else {
			 return ResponseEntity.ok().body(
						Response.builder()
							.code(000)
							.message("Employee Successfully Registered")
							.body(employeeHelper.dtoMapper(employeeRespository.save(employeeHelper.modelMapper(request))))
							.build());
		 }
		
		
	}

	@Override
	public ResponseEntity<Response> fetchEmployeeRecords() {
		
		List<EmployeeModel> empListObj = employeeRespository.findAll();
		
		if(empListObj.isEmpty()) {
			return ResponseEntity.ok().body(
					Response.builder()
						.code(999)
						.message("No employes available in the database")
						.build()
				);
		} else {
			return ResponseEntity.ok().body(
					Response.builder()
						.code(000)
						.message("All Employee Records has been fetched")
						.body(empListObj.stream().map(empModel -> employeeHelper.dtoMapper(empModel)).collect(Collectors.toList()))
						.build()
				);
		}
		
	}

	@Override
	public ResponseEntity<Response> fetchEmployeeRecord(Long id) {
		
		Optional<EmployeeModel> obj =  employeeRespository.findById(id);
		if(obj.isPresent()) {
			return ResponseEntity.ok().body(
					Response.builder()
						.code(000)
						.message("Employee Record has been fetched")
						.body(obj.stream().map(empModel -> employeeHelper.dtoMapper(empModel)))
						.build()
				);
		} else {
			return ResponseEntity.ok().body(
					Response.builder()
						.code(999)
						.message("Employee Record not present in the database")
						.build()
				);
		}
		
		
	}

	@Override
	public ResponseEntity<Response> updateEmployeeRecord(Long id) {
		
		Optional<EmployeeModel> emplModelObj =  employeeRespository.findById(id);
		EmployeeModel model = new EmployeeModel();
		if(emplModelObj.isPresent()) {
			model = emplModelObj.get();
			model.setEmployeeAddress("DSO, UAE");
			model = employeeRespository.save(model);
			return ResponseEntity.ok().body(
					Response.builder()
						.code(000)
						.message("Employee Record has been updated")
						.body(employeeHelper.dtoMapper(model))
						.build());
		} else {
			return ResponseEntity.ok().body(
					Response.builder()
						.code(999)
						.message("Employee Record not present in the database")
						.build());
		}
		
	}

	@Override
	public ResponseEntity<Response> deleteEmployeeRecord(Long id) {
		
		employeeRespository.deleteById(id);
		
		return ResponseEntity.ok().body(
				Response.builder()
					.code(000)
					.message("Employee Record has been deleted")
					.build());
	}

	

}

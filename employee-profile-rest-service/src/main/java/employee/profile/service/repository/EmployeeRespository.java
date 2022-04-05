package employee.profile.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee.profile.service.model.EmployeeModel;

@Repository
public interface EmployeeRespository extends JpaRepository<EmployeeModel, Long>{
	
	Optional<EmployeeModel> findByEmployeeName(String empName);
}

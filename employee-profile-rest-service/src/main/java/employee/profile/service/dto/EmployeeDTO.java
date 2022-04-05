package employee.profile.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class EmployeeDTO {

	private Long id;
	private String employeeName;
	private String employeeAddress;
	private int yearsOfExp;
	private int employeeAge;
}

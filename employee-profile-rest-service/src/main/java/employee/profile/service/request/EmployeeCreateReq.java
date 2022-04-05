package employee.profile.service.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
public class EmployeeCreateReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9212276994094975010L;
	
	@NotBlank(message = "Employee name is mandatory")
	private String employeeName;
	
	@NotBlank(message = "Employee address is mandatory")
	private String employeeAddress;
	private int yearsOfExp;
	private int employeeAge;
}

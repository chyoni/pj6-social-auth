package cwchoiit.socialauth.service.response;

import cwchoiit.socialauth.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;

public record EmployeeReadResponse(@Schema(example = "1", description = "Employee ID(PK)") Long employeeId,
                                   @Schema(example = "Anthony", description = "First Name of Employee") String firstName,
                                   @Schema(example = "Hops", description = "Last Name of Employee") String lastName,
                                   @Schema(example = "2", description = "Department ID(PK) of this employee") Long departmentId) {

    public static EmployeeReadResponse of(Employee employee) {
        return new EmployeeReadResponse(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId()
        );
    }
}

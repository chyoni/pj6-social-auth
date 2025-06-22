package cwchoiit.socialauth.service.response;

import cwchoiit.socialauth.entity.Department;
import io.swagger.v3.oas.annotations.media.Schema;

public record DepartmentReadResponse(@Schema(example = "1", description = "Department ID(PK)") Long departmentId,
                                     @Schema(example = "IT Team", description = "Department Name") String deptName,
                                     @Schema(example = "2", description = "Team Leader ID") Long teamLeadId) {

    public static DepartmentReadResponse of(Department department) {
        return new DepartmentReadResponse(
                department.getDepartmentId(),
                department.getDeptName(),
                department.getTeamLeadId()
        );
    }
}

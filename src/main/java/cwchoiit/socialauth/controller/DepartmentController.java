package cwchoiit.socialauth.controller;

import cwchoiit.socialauth.service.DepartmentService;
import cwchoiit.socialauth.service.response.DepartmentReadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/departments")
@Tag(name = "Department API", description = "Department API")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Find all departments")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartmentReadResponse>> findDepartments() {
        return ResponseEntity.ok(departmentService.findDepartments());
    }
}

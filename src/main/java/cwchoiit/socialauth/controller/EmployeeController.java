package cwchoiit.socialauth.controller;

import cwchoiit.socialauth.service.EmployeeService;
import cwchoiit.socialauth.service.response.EmployeeReadResponse;
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
@RequestMapping("api/v1/employees")
@Tag(name = "Employee API", description = "Employee API")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Find all employees")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeReadResponse>> findEmployees() {
        return ResponseEntity.ok(employeeService.findEmployees());
    }
}

package cwchoiit.socialauth.service;

import cwchoiit.socialauth.entity.Employee;
import cwchoiit.socialauth.repository.EmployeeRepository;
import cwchoiit.socialauth.service.request.EmployeeCreateRequest;
import cwchoiit.socialauth.service.response.EmployeeReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeReadResponse> findEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeReadResponse::of)
                .toList();
    }

    @Transactional
    public EmployeeReadResponse create(EmployeeCreateRequest request) {
        Employee newEmployee = employeeRepository.save(
                Employee.create(
                        request.firstName(),
                        request.lastName(),
                        request.departmentId()
                )
        );

        return EmployeeReadResponse.of(newEmployee);
    }
}

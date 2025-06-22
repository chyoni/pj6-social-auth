package cwchoiit.socialauth.service;

import cwchoiit.socialauth.repository.DepartmentRepository;
import cwchoiit.socialauth.service.response.DepartmentReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentReadResponse> findDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentReadResponse::of)
                .toList();
    }
}

package cwchoiit.socialauth.repository;

import cwchoiit.socialauth.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

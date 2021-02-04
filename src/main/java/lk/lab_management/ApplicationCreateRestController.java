package lk.lab_management;

import lk.lab_management.asset.common_asset.model.enums.CivilStatus;
import lk.lab_management.asset.common_asset.model.enums.Gender;
import lk.lab_management.asset.common_asset.model.enums.Title;
import lk.lab_management.asset.employee.entity.Employee;
import lk.lab_management.asset.employee.entity.enums.Designation;
import lk.lab_management.asset.employee.entity.enums.EmployeeStatus;
import lk.lab_management.asset.employee.service.EmployeeService;
import lk.lab_management.asset.user_management.entity.Role;
import lk.lab_management.asset.user_management.entity.User;
import lk.lab_management.asset.user_management.service.RoleService;
import lk.lab_management.asset.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
public class ApplicationCreateRestController {
    private final RoleService roleService;
    private final UserService userService;
    private final EmployeeService employeeService;


    @Autowired
    public ApplicationCreateRestController(RoleService roleService, UserService userService,
                                           EmployeeService employeeService) {
        this.roleService = roleService;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping( "/select/user" )
    public String saveUser() {
        //roles list start
        String[] roles = {"ADMIN","SQA", "QA", "TM", "LA", "CA", "HR", "ACC"};
        for ( String s : roles ) {
            Role role = new Role();
            role.setRoleName(s);
            roleService.persist(role);
        }

//Employee
        Employee employee = new Employee();
        employee.setCode("11111111");
        employee.setName("Admin User");
        employee.setCallingName("Admin");
        employee.setName("908670000V");
        employee.setMobileOne("0750000000");
        employee.setTitle(Title.MR);
        employee.setGender(Gender.MALE);
        employee.setDesignation(Designation.LA);
        employee.setCivilStatus(CivilStatus.UNMARRIED);
        employee.setEmployeeStatus(EmployeeStatus.WORKING);
        employee.setDateOfBirth(LocalDate.now().minusYears(18));
        employee.setDateOfAssignment(LocalDate.now());
        Employee employeeDb = employeeService.persist(employee);


        //admin user one
        User user = new User();
        user.setEmployee(employeeDb);
        user.setUsername("admin");
        user.setPassword("admin");
        String message = "Username:- " + user.getUsername() + "\n Password:- " + user.getPassword();
        user.setEnabled(true);
        user.setRoles(roleService.findAll()
                              .stream()
                              .filter(role -> role.getRoleName().equals("ADMIN"))
                              .collect(Collectors.toList()));
        userService.persist(user);

        return message;
    }


}

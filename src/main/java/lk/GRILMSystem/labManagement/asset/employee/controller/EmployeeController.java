package lk.GRILMSystem.labManagement.asset.employee.controller;

import lk.GRILMSystem.labManagement.asset.commonAsset.service.CommonService;
import lk.GRILMSystem.labManagement.asset.employee.entity.Employee;
import lk.GRILMSystem.labManagement.asset.employee.entity.EmployeeFiles;
import lk.GRILMSystem.labManagement.asset.employee.entity.Enum.EmployeeStatus;
import lk.GRILMSystem.labManagement.asset.employee.service.EmployeeFilesService;
import lk.GRILMSystem.labManagement.asset.employee.service.EmployeeService;
import lk.GRILMSystem.labManagement.asset.userManagement.entity.User;
import lk.GRILMSystem.labManagement.asset.userManagement.service.UserService;
import lk.GRILMSystem.labManagement.util.service.DateTimeAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestMapping( "/employee" )
@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeFilesService employeeFilesService;
    private final DateTimeAgeService dateTimeAgeService;
    private final CommonService commonService;
    private final UserService userService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeFilesService employeeFilesService,
                              DateTimeAgeService dateTimeAgeService, CommonService commonService,
                              UserService userService) {
        this.employeeService = employeeService;
        this.employeeFilesService = employeeFilesService;

        this.dateTimeAgeService = dateTimeAgeService;
        this.commonService = commonService;
        this.userService = userService;
    }
//----> Employee details management - start <----//

    // Common things for an employee add and update
    private String commonThings(Model model) {
        commonService.commonEmployeeAndOffender(model);
        return "employee/addEmployee";
    }

    //When scr called file will send to
    @GetMapping( "/file/{filename}" )
    public ResponseEntity< byte[] > downloadFile(@PathVariable( "filename" ) String filename) {
        EmployeeFiles file = employeeFilesService.findByNewID(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getPic());
    }

    //Send all employee data
    @RequestMapping
    public String employeePage(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employee/employee";
    }

    //Send on employee details
    @GetMapping( value = "/{id}" )
    public String employeeView(@PathVariable( "id" ) Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employeeDetail", employee);
        model.addAttribute("addStatus", false);
        model.addAttribute("files", employeeFilesService.employeeFileDownloadLinks(employee));
        return "employee/employee-detail";
    }

    //Send employee data edit
    @GetMapping( value = "/edit/{id}" )
    public String editEmployeeForm(@PathVariable( "id" ) Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("newEmployee", employee.getCode());
        model.addAttribute("addStatus", false);
        model.addAttribute("files", employeeFilesService.employeeFileDownloadLinks(employee));
        return commonThings(model);
    }

    //Send an employee add form
    @GetMapping( value = {"/add"} )
    public String employeeAddForm(Model model) {
        model.addAttribute("addStatus", true);
        model.addAttribute("employee", new Employee());
        return commonThings(model);
    }

    //Employee add and update
    @PostMapping( value = {"/add", "/update"} )
    public String addEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, Model model
                             ) {

        if ( result.hasErrors() ) {
            model.addAttribute("addStatus", true);
            model.addAttribute("employee", employee);
            return commonThings(model);
        }
        try {
            employee.setMobileOne(commonService.commonMobileNumberLengthValidator(employee.getMobileOne()));
            employee.setMobileTwo(commonService.commonMobileNumberLengthValidator(employee.getMobileTwo()));
            employee.setLand(commonService.commonMobileNumberLengthValidator(employee.getLand()));
            //after save employee files and save employee
            employeeService.persist(employee);

            //if employee state is not working he or she cannot access to the system
            if ( !employee.getEmployeeStatus().equals(EmployeeStatus.WORKING) ) {
                User user = userService.findUserByEmployee(employeeService.findByNic(employee.getNic()));
                //if employee not a user
                if ( user != null ) {
                    user.setEnabled(false);
                    userService.persist(user);
                }
            }
            //save employee images file
            for ( MultipartFile file : employee.getFiles() ) {
                if ( file.getOriginalFilename() != null ) {
                    EmployeeFiles employeeFiles = employeeFilesService.findByName(file.getOriginalFilename());
                    if ( employeeFiles != null ) {
                        // update new contents
                        employeeFiles.setPic(file.getBytes());
                        // Save all to database
                    } else {
                        employeeFiles = new EmployeeFiles(file.getOriginalFilename(),
                                                          file.getContentType(),
                                                          file.getBytes(),
                                                          employee.getNic().concat("-" + LocalDateTime.now()),
                                                          UUID.randomUUID().toString().concat("employee"));
                        employeeFiles.setEmployee(employee);
                    }
                    employeeFilesService.persist(employeeFiles);
                }
            }
            return "redirect:/employee";

        } catch ( Exception e ) {
            ObjectError error = new ObjectError("employee",
                                                "There is already in the system. <br>System message -->" + e.toString());
            result.addError(error);
            model.addAttribute("addStatus", true);
            model.addAttribute("employee", employee);
            return commonThings(model);
        }
    }

    //If need to employee {but not applicable for this }
    @GetMapping( value = "/remove/{id}" )
    public String removeEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }

    //To search employee any giving employee parameter
    @GetMapping( value = "/search" )
    public String search(Model model, Employee employee) {
        model.addAttribute("employeeDetail", employeeService.search(employee));
        return "employee/employee-detail";
    }


    }
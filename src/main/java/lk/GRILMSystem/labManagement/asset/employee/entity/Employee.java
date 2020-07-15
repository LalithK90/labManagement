package lk.GRILMSystem.labManagement.asset.employee.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.commonAsset.model.Enum.BloodGroup;
import lk.GRILMSystem.labManagement.asset.commonAsset.model.Enum.CivilStatus;
import lk.GRILMSystem.labManagement.asset.commonAsset.model.Enum.Gender;
import lk.GRILMSystem.labManagement.asset.commonAsset.model.Enum.Title;
import lk.GRILMSystem.labManagement.asset.commonAsset.model.FileInfo;
import lk.GRILMSystem.labManagement.asset.employee.entity.Enum.Designation;
import lk.GRILMSystem.labManagement.asset.employee.entity.Enum.EmployeeStatus;
import lk.GRILMSystem.labManagement.asset.message.entity.EmailMessage;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "Employee" )
public class Employee extends AuditEntity {

    @Column( unique = true )
    private String employeeId;

    @Size( min = 5, message = "Your name cannot be accepted" )
    private String name;

    @Size( min = 5, message = "At least 5 characters should be included calling name" )
    private String callingName;

    @Size( max = 12, min = 10, message = "NIC number is contained numbers between 9 and X/V or 12 " )
    @Column( unique = true )
    private String nic;

    @Size( max = 10, message = "Mobile number length should be contained 10 and 9" )
    private String mobileOne;

    @Size( max = 10, message = "Mobile number length should be contained 10 and 9" )
    private String mobileTwo;

    @Size( max = 10, message = "Phone number length should be contained 10 and 9" )
    private String land;

    @Column( unique = true )
    private String officeEmail;

    @Column( columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL", length = 255 )
    private String address;

    @Enumerated( EnumType.STRING )
    private Title title;

    @Enumerated( EnumType.STRING )
    private Gender gender;

    @Enumerated( EnumType.STRING )
    private Designation designation;

    @Enumerated( EnumType.STRING )
    private CivilStatus civilStatus;

    @Enumerated( EnumType.STRING )
    private EmployeeStatus employeeStatus;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate dateOfBirth;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate dateOfAssignment;

    @ManyToMany( mappedBy = "employees" )
    private List<EmailMessage> emailMessages;

    @Transient
    private List< MultipartFile > files = new ArrayList<>();

    @Transient
    private List< String > removeImages = new ArrayList<>();

    @Transient
    private List<FileInfo> fileInfos = new ArrayList<>();

}

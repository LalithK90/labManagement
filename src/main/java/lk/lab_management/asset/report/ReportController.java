package lk.lab_management.asset.report;


import lk.lab_management.asset.common_asset.model.*;
import lk.lab_management.asset.compound.entity.Compound;
import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.customer.entity.Customer;
import lk.lab_management.asset.employee.entity.Employee;
import lk.lab_management.asset.employee.service.EmployeeService;
import lk.lab_management.asset.payment.entity.Payment;
import lk.lab_management.asset.payment.entity.enums.PaymentMethod;
import lk.lab_management.asset.payment.service.PaymentService;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingLabTestService;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingService;
import lk.lab_management.asset.user_management.service.UserService;
import lk.lab_management.util.service.DateTimeAgeService;
import lk.lab_management.util.service.OperatorService;
import org.apache.http.NameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping( "/report" )
public class ReportController {
  private final PaymentService paymentService;
  private final DateTimeAgeService dateTimeAgeService;
  private final UserService userService;
  private final SampleReceivingService sampleReceivingService;
  private final SampleReceivingLabTestService sampleReceivingLabTestService;
  private final EmployeeService employeeService;

  public ReportController(PaymentService paymentService,
                          DateTimeAgeService dateTimeAgeService, UserService userService,
                          SampleReceivingService sampleReceivingService,
                          SampleReceivingLabTestService sampleReceivingLabTestService,
                          EmployeeService employeeService) {
    this.paymentService = paymentService;
    this.dateTimeAgeService = dateTimeAgeService;
    this.userService = userService;
    this.sampleReceivingService = sampleReceivingService;
    this.sampleReceivingLabTestService = sampleReceivingLabTestService;
    this.employeeService = employeeService;
  }

  // test count -> today and date range
  @GetMapping( "/labTestName" )
  public String labTestToday(Model model) {
    LocalDate today = LocalDate.now();
    return commonLabTest(today, today, model);
  }

  @PostMapping( "/labTestName" )
  public String labTestDateRange(@ModelAttribute( "twoDate" ) TwoDate twoDate, Model model) {
    return commonLabTest(twoDate.getStartDate(), twoDate.getEndDate(), model);
  }

  private String commonLabTest(LocalDate startDate, LocalDate endDate, Model model) {
    //according to date
    if ( startDate.equals(endDate) ) {
      List< NameCount > nameCounts = new ArrayList<>();
      for ( LabTestName value : LabTestName.values() ) {
        NameCount nameCount = new NameCount();
        nameCount.setName(value.getLabTestName());
        nameCount.setCount(countAccordingToLabTest(sampleReceivingLabTestService
                                                       .findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate), dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)),
                                                   value).size());
        nameCounts.add(nameCount);
      }
      model.addAttribute("labTestNameCounts", nameCounts);
    } else if ( startDate.isBefore(endDate) ) {
      List< NameCountDate > nameCountDates = new ArrayList<>();
      Period difference = Period.between(startDate, endDate);
      for ( int i = 0; i < difference.getDays(); i++ ) {
        NameCountDate nameCountDate = new NameCountDate();
        List< NameCount > nameCounts = new ArrayList<>();
        for ( LabTestName value : LabTestName.values() ) {
          NameCount nameCount = new NameCount();
          nameCount.setName(value.getLabTestName());
          nameCount.setCount(countAccordingToLabTest(sampleReceivingLabTestService
                                                         .findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate.plusDays(i)), dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)),
                                                     value).size());
          nameCounts.add(nameCount);
        }
        nameCountDate.setLocalDate(startDate.plusDays(i));
        nameCountDate.setNameCounts(nameCounts);
        nameCountDates.add(nameCountDate);
      }
      model.addAttribute("labTestNameCountDates", nameCountDates);
    }
    model.addAttribute("message", " Records from " + startDate + " to " + endDate);
    return "report/labTestName";
  }

  private List< SampleReceivingLabTest > countAccordingToLabTest
      (List< SampleReceivingLabTest > sampleReceivingLabTests, LabTestName labTestName) {
    return sampleReceivingLabTests.stream().filter(x -> x.getLabTestName().equals(labTestName)).collect(Collectors.toList());
  }

  //income -> today and date range
  @GetMapping( "/income" )
  public String incomeToday(Model model) {
    LocalDate today = LocalDate.now();
    return commonIncome(today, today, model);
  }

  @PostMapping( "/income" )
  public String incomeDateRange(@ModelAttribute( "twoDate" ) TwoDate twoDate, Model model) {
    return commonIncome(twoDate.getStartDate(), twoDate.getEndDate(), model);
  }

  private String commonIncome(LocalDate startDate, LocalDate endDate, Model model) {
    List< Payment > payments =
        paymentService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate),
                                                dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate));
    List< NameCountUserPaymentTypeAmount > nameCountUserPaymentTypeAmounts = new ArrayList<>();
    userNames(payments).forEach(x -> {
      NameCountUserPaymentTypeAmount nameCountUserPaymentTypeAmount = new NameCountUserPaymentTypeAmount();
      Employee employee = employeeService.findById(userService.findByUserName(x).getEmployee().getId());
      nameCountUserPaymentTypeAmount.setName(employee.getTitle().getTitle() + "  " + employee.getName());

      List< Payment > paymentsByUser = paymentsByUserName(payments, x);

      nameCountUserPaymentTypeAmount.setCount(paymentsByUser.size());

      List< PaymentTypeAmount > paymentTypeAmounts = new ArrayList<>();

      for ( PaymentMethod value : PaymentMethod.values() ) {
        PaymentTypeAmount paymentTypeAmount = new PaymentTypeAmount();
        paymentTypeAmount.setPaymentMethod(value);
        List< BigDecimal > paymentTypeAmountDb = new ArrayList<>();
        paymentsByUser
            .stream()
            .filter(payment -> payment.getPaymentMethod().equals(value))
            .collect(Collectors.toList())
            .forEach(payment -> paymentTypeAmountDb.add(payment.getAmount()));

        paymentTypeAmount.setAmount(paymentTypeAmountDb.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        paymentTypeAmounts.add(paymentTypeAmount);
      }
      nameCountUserPaymentTypeAmount.setPaymentTypeAmounts(paymentTypeAmounts);
      nameCountUserPaymentTypeAmounts.add(nameCountUserPaymentTypeAmount);
    });
    model.addAttribute("message", " Records from " + startDate + " to " + endDate);
    model.addAttribute("nameCountUserPaymentTypeAmounts", nameCountUserPaymentTypeAmounts);
    return "report/income";
  }

  private List< String > userNames(List< Payment > payments) {
    List< String > userNames = new ArrayList<>();
    payments.forEach(x -> userNames.add(x.getCreatedBy()));
    return userNames.stream().distinct().collect(Collectors.toList());
  }

  private List< Payment > paymentsByPaymentMethode(List< Payment > payments, PaymentMethod paymentMethod) {
    return payments
        .stream()
        .filter(x -> x.getPaymentMethod().equals(paymentMethod))
        .collect(Collectors.toList());
  }

  private List< Payment > paymentsByUserName(List< Payment > payments, String name) {
    return payments
        .stream()
        .filter(x -> x.getCreatedBy().equals(name))
        .collect(Collectors.toList());
  }

  private List< BigDecimal > priceList(List< Payment > payments) {
    List< BigDecimal > priceList = new ArrayList<>();
    payments.forEach(x -> priceList.add(x.getAmount()));
    return priceList;
  }

  //customers sample count -> today and date range
  @GetMapping( "/customer" )
  public String customerToday(Model model) {
    LocalDate today = LocalDate.now();
    return commonCustomer(today, today, model);
  }

  @PostMapping( "/customer" )
  public String customerDateRange(@ModelAttribute( "twoDate" ) TwoDate twoDate, Model model) {
    return commonCustomer(twoDate.getStartDate(), twoDate.getEndDate(), model);
  }

  private String commonCustomer(LocalDate startDate, LocalDate endDate, Model model) {
    model.addAttribute("customerNameSampleCounts",
                       customerNameSampleCount(sampleReceivingService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate),
                                                                                               dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate))));

    model.addAttribute("message", " Records from " + startDate + " to " + endDate);
    return "report/customerNameSampleCount";
  }

  private List< NameCount > customerNameSampleCount(List< SampleReceiving > sampleReceiving) {
    List< NameCount > nameCounts = new ArrayList<>();
    HashSet< Customer > customers = new HashSet<>();
    sampleReceiving.forEach(x -> customers.add(x.getCustomer()));
    for ( Customer customer : customers ) {
      NameCount nameCount = new NameCount();
      nameCount.setName(customer.getName());
      nameCount.setCount((int) sampleReceiving
          .stream()
          .filter(x -> x.getCustomer().equals(customer)).count());
      nameCounts.add(nameCount);
    }
    return nameCounts;
  }

  //compounded type and account -> today and date range
  @GetMapping( "/compound" )
  public String compoundToday(Model model) {
    LocalDate today = LocalDate.now();
    return commonCompound(today, today, model);
  }

  @PostMapping( "/compound" )
  public String compoundDateRange(@ModelAttribute( "twoDate" ) TwoDate twoDate, Model model) {
    return commonCompound(twoDate.getStartDate(), twoDate.getEndDate(), model);
  }

  private String commonCompound(LocalDate startDate, LocalDate endDate, Model model) {
    model.addAttribute("compoundNameAndCounts",
                       compoundNameAndCount(sampleReceivingService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate),
                                                                                            dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate))));

    model.addAttribute("message", " Records from " + startDate + " to " + endDate);
    return "report/compound";
  }

  private List< NameCount > compoundNameAndCount(List< SampleReceiving > sampleReceiving) {
    List< NameCount > nameCounts = new ArrayList<>();
    compoundHashSet(sampleReceiving).forEach(x -> {
      NameCount nameCount = new NameCount();
      nameCount.setName(x.getName());
      nameCount.setCount((int) sampleReceiving.stream().filter(y -> y.getCompound().equals(x)).count());
      nameCounts.add(nameCount);
    });

    return nameCounts;
  }

  private HashSet< Compound > compoundHashSet(List< SampleReceiving > sampleReceiving) {
    HashSet< Compound > compounds = new HashSet<>();
    sampleReceiving.forEach(x -> compounds.add(x.getCompound()));
    return compounds;
  }
}

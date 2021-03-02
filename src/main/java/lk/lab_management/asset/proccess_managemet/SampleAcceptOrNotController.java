package lk.lab_management.asset.proccess_managemet;

import lk.lab_management.asset.compound.entity.Specification;
import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.compound.service.SpecificationService;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTestResult;
import lk.lab_management.asset.sample_receiving.entity.enums.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.enums.LabTestResultStatus;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingLabTestStatus;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingStatus;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingLabTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( "/sample" )
public class SampleAcceptOrNotController {
  private final SampleReceivingLabTestService sampleReceivingLabTestService;
  private final SpecificationService specificationService;

  public SampleAcceptOrNotController(SampleReceivingLabTestService sampleReceivingLabTestService,
                                     SpecificationService specificationService) {
    this.sampleReceivingLabTestService = sampleReceivingLabTestService;
    this.specificationService = specificationService;
  }

  @GetMapping( "/acceptability" )
  public String sampleAcceptOrNotSelection(Model model) {
    model.addAttribute("labTestNames", LabTestName.values());
    model.addAttribute("showList", false);
    return "processManagement/sampleAcceptOrNot";
  }

  @GetMapping( "/acceptability/{labTestName}" )
  public String sampleAcceptOrNotSelection(@PathVariable LabTestName labTestName, Model model) {
    model.addAttribute("sampleReceivingLabTests",
                       sampleReceivingLabTestService.findByLabTestNameAndAcceptability(labTestName,
                                                                                       Acceptability.PENDING));
    model.addAttribute("showList", true);
    return "processManagement/sampleAcceptOrNot";
  }

  @GetMapping( "/acceptability/accept/{id}" )
  public String sampleAcceptOrNotAccept(@PathVariable Integer id, Model model) {
    SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
    //sampleReceivingLabTest SampleReceivingStatus checked
    if ( sampleReceivingLabTest.getAcceptability().equals(Acceptability.PENDING) ) {
      sampleReceivingLabTest.setAcceptability(Acceptability.ACCEPT);
      sampleReceivingLabTest.setSampleReceivingLabTestStatus(SampleReceivingLabTestStatus.NOTRESULTENTER);
      // sample receiving status is active sampleReceiving
      SampleReceiving sampleReceiving = sampleReceivingLabTest.getSampleReceiving();
      sampleReceiving.setSampleReceivingStatus(SampleReceivingStatus.ACTIVE);
      sampleReceivingLabTest.setSampleReceiving(sampleReceiving);
      // sampleReceivingLabTestService.persist(sampleReceivingLabTest);
//specification list
      List< Specification > specificationList = specificationService
          .findByLabTestNameAndCompound(sampleReceivingLabTest.getLabTestName(),
                                        sampleReceivingLabTest.getSampleReceiving().getCompound());
      //create sample receiving lab test result list and create new row to that table with null result
      List< SampleReceivingLabTestResult > sampleReceivingLabTestResults = new ArrayList<>();
      for ( Specification specification : specificationList ) {
        System.out.println(specification.getId());
        SampleReceivingLabTestResult sampleReceivingLabTestResult =
            new SampleReceivingLabTestResult((float) 0.0, LabTestResultStatus.FAIL, sampleReceivingLabTest, specification);
        sampleReceivingLabTestResults.add(sampleReceivingLabTestResult);
      }
      sampleReceivingLabTest.setSampleReceivingLabTestResults(sampleReceivingLabTestResults);
      sampleReceivingLabTestService.persist(sampleReceivingLabTest);
    }
    model.addAttribute("sampleReceivingLabTests",
                       sampleReceivingLabTestService.findByLabTestNameAndAcceptability(sampleReceivingLabTest.getLabTestName(), Acceptability.PENDING));
    model.addAttribute("showList", true);
    return "processManagement/sampleAcceptOrNot";
  }

  @GetMapping( "/acceptability/reject/{id}" )
  public String sampleAcceptOrNotReject(@PathVariable Integer id, Model model) {
    SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
    //sampleReceivingLabTest SampleReceivingStatus checked
    if ( sampleReceivingLabTest.getAcceptability().equals(Acceptability.PENDING) ) {
      sampleReceivingLabTest.setAcceptability(Acceptability.REJECT);
      // sample receiving status is active sampleReceiving

      SampleReceiving sampleReceiving = sampleReceivingLabTest.getSampleReceiving();
      sampleReceiving.setSampleReceivingStatus(SampleReceivingStatus.DISABLED);
      sampleReceivingLabTest.setSampleReceiving(sampleReceiving);

      sampleReceivingLabTestService.persist(sampleReceivingLabTest);
    }
    model.addAttribute("sampleReceivingLabTests",
                       sampleReceivingLabTestService.findByLabTestNameAndAcceptability(sampleReceivingLabTest.getLabTestName(), Acceptability.PENDING));
    model.addAttribute("showList", true);
    return "processManagement/sampleAcceptOrNot";

  }
}

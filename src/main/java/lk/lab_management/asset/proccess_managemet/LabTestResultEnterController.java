package lk.lab_management.asset.proccess_managemet;

import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTestResult;
import lk.lab_management.asset.sample_receiving.entity.enums.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingLabTestStatus;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingStatus;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingLabTestResultService;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingLabTestService;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( "/labTestResultEnter" )
public class LabTestResultEnterController {
  private final SampleReceivingLabTestService sampleReceivingLabTestService;
  private final SampleReceivingLabTestResultService sampleReceivingLabTestResultService;
  private final SampleReceivingService sampleReceivingService;


  public LabTestResultEnterController(SampleReceivingLabTestService sampleReceivingLabTestService,
                                      SampleReceivingLabTestResultService sampleReceivingLabTestResultService,
                                      SampleReceivingService sampleReceivingService) {
    this.sampleReceivingLabTestService = sampleReceivingLabTestService;
    this.sampleReceivingLabTestResultService = sampleReceivingLabTestResultService;
    this.sampleReceivingService = sampleReceivingService;
  }

  @GetMapping( "/form" )
  public String sampleAcceptOrNotSelection(Model model) {
    model.addAttribute("labTestNames", LabTestName.values());
    model.addAttribute("showList", false);
    return "processManagement/labTestResultEnter";
  }

  //get all the accepted samples
  //NOTRESULTENTER samples
  @GetMapping( "/form/{labTestName}" )
  public String sampleAcceptOrNotSelection(@PathVariable LabTestName labTestName, Model model) {
    model.addAttribute("sampleReceivingLabTests",
                       sampleReceivingLabTestService.findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus
                           (labTestName, Acceptability.ACCEPT, SampleReceivingLabTestStatus.NOTRESULTENTER));
    model.addAttribute("showList", true);
    return "processManagement/labTestResultEnter";
  }

  @GetMapping( "/form/add/{id}" )
  public String resultEnterAddForm(@PathVariable Integer id, Model model) {
    SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
    List< SampleReceivingLabTestResult > sampleReceivingLabTestResults = new ArrayList<>();
    for ( SampleReceivingLabTestResult sampleReceivingLabTestResult :
        sampleReceivingLabTest.getSampleReceivingLabTestResults() ) {
      SampleReceivingLabTestResult sampleReceivingLabTestResultDB =
          sampleReceivingLabTestResultService.findById(sampleReceivingLabTestResult.getId());
      sampleReceivingLabTestResults.add(sampleReceivingLabTestResultDB);
    }

    model.addAttribute("customerDetail", sampleReceivingLabTest.getSampleReceiving().getCustomer());
    model.addAttribute("sampleReceivingLabTest", sampleReceivingLabTest);
    model.addAttribute("sampleReceivingLabTestResultses", sampleReceivingLabTestResults);
    model.addAttribute("addStatus", true);
    return "processManagement/labTestResultEnterForm";
  }


  @GetMapping( "/form/edit/{id}" )
  public String resultEnterEditForm(@PathVariable Integer id, Model model) {
    SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
    commonMethod(model, sampleReceivingLabTest);
    model.addAttribute("addStatus", false);
    return "processManagement/labTestResultEnterForm";
  }

  @GetMapping( "/form/print/{id}" )
  public String resultEnterPrintForm(@PathVariable Integer id, Model model) {
    SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
    commonMethod(model, sampleReceivingLabTest);
    return "processManagement/labTestResultPrintForm";
  }

  private void commonMethod(Model model, SampleReceivingLabTest sampleReceivingLabTest) {
    List< SampleReceivingLabTestResult > sampleReceivingLabTestResults =
        sampleReceivingLabTest.getSampleReceivingLabTestResults();
    model.addAttribute("customerDetail", sampleReceivingLabTest.getSampleReceiving().getCustomer());
    model.addAttribute("sampleReceivingLabTest", sampleReceivingLabTest);
    model.addAttribute("sampleReceivingLabTestResultses", sampleReceivingLabTestResults);
  }


  @PostMapping( "/save" )
  public String labTestResultSave(@ModelAttribute SampleReceivingLabTest sampleReceivingLabTest,
                                  BindingResult result, RedirectAttributes redirectAttributes, Model model) {
    if ( result.hasErrors() ) {
      commonMethod(model, sampleReceivingLabTest);
      model.addAttribute("addStatus", true);
      return "processManagement/labTestResultEnterForm";
    }
    SampleReceivingLabTest sampleReceivingLabTestBeforeSave =
        sampleReceivingLabTestService.findById(sampleReceivingLabTest.getId());
    sampleReceivingLabTestBeforeSave.setSampleReceivingLabTestStatus(SampleReceivingLabTestStatus.RESULTENTER);
    sampleReceivingLabTestBeforeSave.setSampleReceivingLabTestResults(sampleReceivingLabTest.getSampleReceivingLabTestResults());
    SampleReceivingLabTest sampleReceivingLabTestDB =
        sampleReceivingLabTestService.persist(sampleReceivingLabTestBeforeSave);

    SampleReceiving sampleReceiving =
        sampleReceivingService.findById(sampleReceivingLabTestDB.getSampleReceiving().getId());

    if ( sampleReceiving.getAmount().equals(new BigDecimal ("0.00")) ) {
      sampleReceiving.setSampleReceivingStatus(SampleReceivingStatus.PAID);
      sampleReceivingService.persist(sampleReceiving);
    }

    return "redirect:/labTestResultEnter/form/" + sampleReceivingLabTestDB.getLabTestName();
  }

  //view results entered
  @GetMapping( "/form/{labTestName}/view" )
  public String viewResultEnteredSamples(@PathVariable LabTestName labTestName, Model model) {
    model.addAttribute("sampleReceivingLabTests",
                       sampleReceivingLabTestService.findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus
                           (labTestName, Acceptability.ACCEPT, SampleReceivingLabTestStatus.RESULTENTER));
    model.addAttribute("showList", true);
    return "processManagement/labTestResults";
  }

  @GetMapping( "/form/view/{id}" )
  public String resultEnterViewForm(@PathVariable Integer id, Model model) {
    SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
    commonMethod(model, sampleReceivingLabTest);
    model.addAttribute("addStatus", false);
    return "processManagement/labTestResults-detail";
  }
}

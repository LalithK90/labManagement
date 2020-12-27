package lk.lab_management.asset.proccess_managemet;

import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTestResult;
import lk.lab_management.asset.sample_receiving.entity.enums.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingLabTestStatus;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingLabTestResultService;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingLabTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( "/labTestResultEnter" )
public class LabTestResultEnterController {
  private final SampleReceivingLabTestService sampleReceivingLabTestService;
  private final SampleReceivingLabTestResultService sampleReceivingLabTestResultService;


  public LabTestResultEnterController(SampleReceivingLabTestService sampleReceivingLabTestService,
                                      SampleReceivingLabTestResultService sampleReceivingLabTestResultService) {
    this.sampleReceivingLabTestService = sampleReceivingLabTestService;
    this.sampleReceivingLabTestResultService = sampleReceivingLabTestResultService;
  }

  @GetMapping( "/form" )
  public String sampleAcceptOrNotSelection(Model model) {
    model.addAttribute("labTestNames", LabTestName.values());
    model.addAttribute("showList", false);
    return "processManagement/labTestResultEnter";
  }

  @GetMapping( "/form/{labTestName}" )
  public String sampleAcceptOrNotSelection(@PathVariable LabTestName labTestName, Model model) {
    model.addAttribute("sampleReceivingLabTests",
                       sampleReceivingLabTestService.findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus(labTestName, Acceptability.ACCEPT, SampleReceivingLabTestStatus.NOTRESULTENTER));
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
    //todo:
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

    return "redirect:/labTestResultEnter/form/" + sampleReceivingLabTestDB.getLabTestName();
  }
}

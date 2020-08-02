package lk.GRILMSystem.labManagement.asset.proccessManagemet;

import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.LabTestName;
import lk.GRILMSystem.labManagement.asset.compound.entity.Specification;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.Acceptability;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.SampleReceivingLabTestStatus;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTest;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTestResult;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.service.SampleReceivingLabTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/labTestResultEnter")
public class LabTestResultEnterController {
    private final SampleReceivingLabTestService sampleReceivingLabTestService;


    public LabTestResultEnterController(SampleReceivingLabTestService sampleReceivingLabTestService) {
        this.sampleReceivingLabTestService = sampleReceivingLabTestService;
    }

    @GetMapping("/form")
    public String sampleAcceptOrNotSelection(Model model) {
        model.addAttribute("labTestNames", LabTestName.values());
        model.addAttribute("showList", false);
        return "processManagement/labTestResultEnter";
    }

    @GetMapping("/form/{labTestName}")
    public String sampleAcceptOrNotSelection(@PathVariable LabTestName labTestName, Model model) {
        model.addAttribute("sampleReceivingLabTests", sampleReceivingLabTestService.findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus(labTestName, Acceptability.ACCEPT, SampleReceivingLabTestStatus.NOTRESULTENTER));
        model.addAttribute("showList", true);
        return "processManagement/labTestResultEnter";
    }

    @GetMapping("/form/add/{id}")
    public String resultEnterAddForm(@PathVariable Integer id, Model model) {
        SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
        List<SampleReceivingLabTestResult> sampleReceivingLabTestResults = sampleReceivingLabTest.getSampleReceivingLabTestResults();


        model.addAttribute("sampleReceivingLabTest", sampleReceivingLabTest);
        model.addAttribute("sampleReceivingLabTestResults", sampleReceivingLabTestResults);
        model.addAttribute("addStatus", false);
        return "processManagement/labTestResultEnterForm";
    }

    @GetMapping("/form/edit/{id}")
    public String resultEnterEditForm(@PathVariable Integer id, Model model) {
        SampleReceivingLabTest sampleReceivingLabTest = sampleReceivingLabTestService.findById(id);
        List<SampleReceivingLabTestResult> sampleReceivingLabTestResults = sampleReceivingLabTest.getSampleReceivingLabTestResults();

        model.addAttribute("sampleReceivingLabTest", sampleReceivingLabTest);
        model.addAttribute("sampleReceivingLabTestResults", sampleReceivingLabTestResults);
        model.addAttribute("addStatus", true);
        return "processManagement/labTestResultEnterForm";
    }


    @PostMapping("/save")
    public String labTestResultSave(@ModelAttribute SampleReceivingLabTest sampleReceivingLabTest, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "redirect:/labTestResultEnter/form".concat(sampleReceivingLabTest.getLabTestName().toString());
        }
        sampleReceivingLabTest.setSampleReceivingLabTestStatus(SampleReceivingLabTestStatus.RESULTENTER);
        SampleReceivingLabTest sampleReceivingLabTestDB = sampleReceivingLabTestService.persist(sampleReceivingLabTest);
        //todo-> email and message are need to configure

        return "redirect:/labTestResultEnter/form".concat(sampleReceivingLabTest.getLabTestName().toString());
    }
}

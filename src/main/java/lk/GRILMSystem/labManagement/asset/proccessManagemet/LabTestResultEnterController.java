package lk.GRILMSystem.labManagement.asset.proccessManagemet;

import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.LabTestName;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.Acceptability;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.SampleReceivingLabTestStatus;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.service.SampleReceivingLabTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

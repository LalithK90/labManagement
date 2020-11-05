package lk.GRILMSystem.labManagement.asset.sampleReceiving.controller;

import lk.GRILMSystem.labManagement.asset.compound.controller.CompoundRestController;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.LabTestName;
import lk.GRILMSystem.labManagement.asset.compound.service.CompoundService;
import lk.GRILMSystem.labManagement.asset.customer.entity.Enum.CustomerType;
import lk.GRILMSystem.labManagement.asset.customer.service.CustomerService;
import lk.GRILMSystem.labManagement.asset.discountRatio.service.DiscountRatioService;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.Acceptability;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceiving;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTest;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.service.SampleReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/sampleReceiving")
public class SampleReceivingController {
    private final SampleReceivingService sampleReceivingService;
    private final CompoundService compoundService;
    private final CustomerService customerService;
    private final DiscountRatioService discountRatioService;

    @Autowired
    public SampleReceivingController(SampleReceivingService sampleReceivingService, CompoundService compoundService, CustomerService customerService, DiscountRatioService discountRatioService) {
        this.sampleReceivingService = sampleReceivingService;
        this.compoundService = compoundService;
        this.customerService = customerService;
        this.discountRatioService = discountRatioService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("sampleReceivings", sampleReceivingService.findAll());
        return "sampleReceiving/sampleReceiving";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("sampleReceiving", new SampleReceiving());
        model.addAttribute("compounds", compoundService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("discountRatios", discountRatioService.findAll());
        model.addAttribute("addStatus", true);
        model.addAttribute("customerType", CustomerType.values());
        model.addAttribute("compoundPriceURI", MvcUriComponentsBuilder
                .fromMethodName(CompoundRestController.class, "getPrice", "")
                .build()
                .toString());
        return "sampleReceiving/addSampleReceiving";
    }

    @PostMapping(value = {"save", "update"})
    public String persist(@Valid @ModelAttribute SampleReceiving sampleReceiving, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sampleReceiving", sampleReceiving);
            model.addAttribute("compounds", compoundService.findAll());
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("discountRatios", discountRatioService.findAll());
            model.addAttribute("addStatus", true);
            return "sampleReceiving/addSampleReceiving";
        }
        // sample receiving lab tests
        List<SampleReceivingLabTest> sampleReceivingLabTests = new ArrayList<>();
        for (LabTestName labTest : LabTestName.values()) {
            SampleReceivingLabTest sampleReceivingLabTest = new SampleReceivingLabTest();
            sampleReceivingLabTest.setAcceptability(Acceptability.PENDING);
            sampleReceivingLabTest.setLabTestName(labTest);
            sampleReceivingLabTest.setSampleReceiving(sampleReceiving);
            sampleReceivingLabTests.add(sampleReceivingLabTest);
        }
        sampleReceiving.setSampleReceivingLabTests(sampleReceivingLabTests);
        sampleReceivingService.persist(sampleReceiving);
        return "redirect:/sampleReceiving/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sampleReceivingService.delete(id);
        return "redirect:/sampleReceiving";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("sampleReceivingDetails", sampleReceivingService.findById(id));
        return "sampleReceiving/sampleReceiving-detail";
    }

}

package lk.lab_management.asset.sample_receiving.controller;

import lk.lab_management.asset.compound.controller.CompoundRestController;
import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.compound.service.CompoundService;
import lk.lab_management.asset.customer.entity.enums.CustomerType;
import lk.lab_management.asset.customer.service.CustomerService;
import lk.lab_management.asset.discount_ratio.service.DiscountRatioService;
import lk.lab_management.asset.sample_receiving.entity.enums.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingService;
import lk.lab_management.util.service.MakeAutoGenerateNumberService;
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
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    public SampleReceivingController(SampleReceivingService sampleReceivingService, CompoundService compoundService, CustomerService customerService, DiscountRatioService discountRatioService, MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.sampleReceivingService = sampleReceivingService;
        this.compoundService = compoundService;
        this.customerService = customerService;
        this.discountRatioService = discountRatioService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("addStatus", false);
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
        if ( sampleReceiving.getId()==null ){
            SampleReceiving lastSample = sampleReceivingService.lastSample();
            if ( lastSample ==null ){
                sampleReceiving.setSampleCode("GRIS"+makeAutoGenerateNumberService.numberAutoGen(null).toString());
            }else{
                sampleReceiving.setSampleCode("GRIS"+makeAutoGenerateNumberService.numberAutoGen(lastSample.getSampleCode().substring(4)).toString());
            }
        }
        sampleReceiving.setSampleReceivingLabTests(sampleReceivingLabTests);

        sampleReceivingService.persist(sampleReceiving);
        return "redirect:/sampleReceiving";
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

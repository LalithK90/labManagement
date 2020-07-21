package lk.GRILMSystem.labManagement.asset.sampleReceiving.controller;

import lk.GRILMSystem.labManagement.asset.compound.service.CompoundService;
import lk.GRILMSystem.labManagement.asset.customer.service.CustomerService;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceiving;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.service.SampleReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/sampleReceiving")
public class SampleReceivingController {
    private final SampleReceivingService sampleReceivingService;
    private final CompoundService compoundService;
    private final CustomerService customerService;

    @Autowired
    public SampleReceivingController(SampleReceivingService sampleReceivingService, CompoundService compoundService, CustomerService customerService) {
        this.sampleReceivingService = sampleReceivingService;
        this.compoundService = compoundService;
        this.customerService = customerService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("sampleReceiving", sampleReceivingService.findAll());
        return "sampleReceiving/sampleReceiving";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("sampleReceiving", new SampleReceiving());
        model.addAttribute("compounds", compoundService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("addStatus", true);
        return "sampleReceiving/addSampleReceiving";
    }

    @PostMapping(value = {"save", "update"})
    public String persist(@Valid @ModelAttribute SampleReceiving sampleReceiving, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sampleReceiving", sampleReceiving);
            model.addAttribute("compounds", compoundService.findAll());
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("addStatus", true);
            return "sampleReceiving/addSampleReceiving";
        }
        sampleReceivingService.persist(sampleReceiving);
        return "redirect:/sampleReceiving/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sampleReceivingService.delete(id);
        return "redirect:/sampleReceiving";
    }

}

package lk.GRILMSystem.labManagement.asset.labTest.controller;

import lk.GRILMSystem.labManagement.asset.LabTestParameter.service.LabTestParameterService;

import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.CompoundPropertyName;
import lk.GRILMSystem.labManagement.asset.labTest.entity.Enum.LabTestStatus;
import lk.GRILMSystem.labManagement.asset.labTest.entity.LabTest;
import lk.GRILMSystem.labManagement.asset.labTest.service.LabTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/labTest")
public class LabTestController {
    private final LabTestService labTestService;
    private final LabTestParameterService labTestParameterService;

    public LabTestController(LabTestService labTestService, LabTestParameterService labTestParameterService) {
        this.labTestService = labTestService;
        this.labTestParameterService = labTestParameterService;
    }

    private String commonThing(Model model, Boolean booleanValue, LabTest labTest) {
        model.addAttribute("addStatus", booleanValue);
        model.addAttribute("labTest", labTest);
        model.addAttribute("labTestParameters",labTestParameterService.findAll());
        model.addAttribute("compoundPropertyNames", CompoundPropertyName.values());
        model.addAttribute("labTestStatus", LabTestStatus.values());
        return "labTest/addLabTest";
    }


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("labTests", labTestService.findAll());
        return "labTest/labTest";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThing(model, false, new LabTest());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("labTestDetail", labTestService.findById(id));
        return "labTest/labTest-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThing(model, true, labTestService.findById(id));
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid LabTest labTest, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return commonThing(model, true, labTest);
        }
        redirectAttributes.addFlashAttribute("labTestDetail", labTestService.persist(labTest));
        return "redirect:/labTest";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        labTestService.delete(id);
        return "redirect:/labTest";
    }
}

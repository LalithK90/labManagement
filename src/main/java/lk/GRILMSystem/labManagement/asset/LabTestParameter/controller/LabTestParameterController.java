package lk.GRILMSystem.labManagement.asset.LabTestParameter.controller;


import lk.GRILMSystem.labManagement.asset.LabTestParameter.entity.LabTestParameter;
import lk.GRILMSystem.labManagement.asset.LabTestParameter.service.LabTestParameterService;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.SpecificationName;
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
@RequestMapping("/labTestParameter")
public class LabTestParameterController{
    private final LabTestParameterService labTestParameterService;

    public LabTestParameterController(LabTestParameterService labTestParameterService) {
        this.labTestParameterService = labTestParameterService;
    }

    private String commonThing(Model model, Boolean booleanValue, LabTestParameter labTestParameter) {
        model.addAttribute("addStatus", booleanValue);
        model.addAttribute("labTestParameter", labTestParameter);
        model.addAttribute("specificationNames", SpecificationName.values());
        return "labTestParameter/addLabTestParameter";
    }


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("labTestParameters", labTestParameterService.findAll());
        return "labTestParameter/labTestParameter";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThing(model, false, new LabTestParameter());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("labTestParameterDetail", labTestParameterService.findById(id));
        return "labTestParameter/labTestParameter-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThing(model, true, labTestParameterService.findById(id));
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid LabTestParameter labTestParameter, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return commonThing(model, true, labTestParameter);
        }
        redirectAttributes.addFlashAttribute("labTestParameterDetail", labTestParameterService.persist(labTestParameter));
        return "redirect:/labTestParameter";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        labTestParameterService.delete(id);
        return "redirect:/labTestParameter";
    }
}

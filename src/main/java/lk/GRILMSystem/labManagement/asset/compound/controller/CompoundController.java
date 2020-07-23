package lk.GRILMSystem.labManagement.asset.compound.controller;

import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.CompoundPropertyName;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.SpecificationName;
import lk.GRILMSystem.labManagement.asset.compound.entity.Specification;
import lk.GRILMSystem.labManagement.asset.compound.service.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/compound")
@Controller
public class CompoundController {

    private final CompoundService compoundService;

    @Autowired
    public CompoundController(CompoundService compoundService) {
        this.compoundService = compoundService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("compounds", compoundService.findAll());
        return "compound/compound";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("compound", new Compound());
        model.addAttribute("compoundPropertyNames", CompoundPropertyName.values());
        model.addAttribute("specificationNames", SpecificationName.values());
        return "compound/addCompound";
    }

    @PostMapping(value = {"/add", "/update"})
    public void addComponent(@Valid @ModelAttribute Compound compound, BindingResult result, Model model) {
        for (Specification s : compound.getSpecifications()) {
            s.setCompound(compound);
        }
        compoundService.persist(compound);

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        compoundService.delete(id);
        return "redirect:/compound";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("compoundDetails", compoundService.findById(id));
        return "compound/compound-detail";
    }

}

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/compound")
@Controller
public class CompoundController {

    private final CompoundService compoundService;

    @Autowired
    public CompoundController(CompoundService compoundService) {
        this.compoundService = compoundService;
    }

    @GetMapping("/compoundView")
    public String compoundView() {
        return "compound/compound";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("compound", new Compound());
        model.addAttribute("compoundPropertyNames", CompoundPropertyName.values());
        model.addAttribute("specificationNames", SpecificationName.values());
        return "compound/addCompound";
    }

    //Send all employee data
    @RequestMapping
    public String compoundPage(Model model) {
        model.addAttribute("compounds", compoundService.findAll());
        return "compound/compound";
    }

    @PostMapping(value = {"/add", "/update"})
    public void addComponent(@Valid @ModelAttribute Compound compound, BindingResult result, Model model) {
        for (Specification s : compound.getSpecifications()) {
            s.setCompound(compound);
        }
        compoundService.persist(compound);

    }

}

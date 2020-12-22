package lk.lab_management.asset.compound.controller;

import lk.lab_management.asset.compound.entity.Compound;
import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.compound.entity.enums.SpecificationName;
import lk.lab_management.asset.compound.entity.Specification;
import lk.lab_management.asset.compound.service.CompoundService;
import lk.lab_management.asset.compound.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
@RequestMapping( "/compound" )
public class CompoundController {

    private final CompoundService compoundService;
    private final SpecificationService specificationService;

    @Autowired
    public CompoundController(CompoundService compoundService, SpecificationService specificationService) {
        this.compoundService = compoundService;
        this.specificationService = specificationService;
    }

  @GetMapping
  public String findAll(Model model) {
    model.addAttribute("compounds", compoundService.findAll());
    return "compound/compound";
  }

  @GetMapping( "/add" )
  public String form(Model model) {
    model.addAttribute("compound", new Compound());
    model.addAttribute("labTests", LabTestName.values());
    model.addAttribute("addStatus", true);
    model.addAttribute("specificationNames", SpecificationName.values());
    return "compound/addCompound";
  }

  @GetMapping( "/edit/{id}" )
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("compound", compoundService.findById(id));
    model.addAttribute("addStatus", false);
    model.addAttribute("labTests", LabTestName.values());
    model.addAttribute("specificationNames", SpecificationName.values());
    return "compound/addCompound";
  }

    @PostMapping(value = {"/add", "/update"})
    public String addComponent(@Valid @ModelAttribute Compound compound, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("compound", compound);
            model.addAttribute("labTests", LabTestName.values());
            model.addAttribute("addStatus", true);
            model.addAttribute("specificationNames", SpecificationName.values());
            return "compound/addCompound";
        }
        Compound compoundDB=   compoundService.persist(compound);
        for (Specification s : compound.getSpecifications()) {
            s.setCompound(compoundDB);
            specificationService.persist(s);
        }
        return "redirect:/compound";
    }
    Compound compoundDB = compoundService.persist(compound);

    for ( Specification s : compound.getSpecifications() ) {
      s.setCompound(compoundDB);
      specificationService.persist(s);
    }
    return "redirect:/compound";
  }

  @GetMapping( "/delete/{id}" )
  public String delete(@PathVariable Integer id, Model model) {
    compoundService.delete(id);
    return "redirect:/compound";
  }

  @GetMapping( "/{id}" )
  public String view(@PathVariable Integer id, Model model) {
    model.addAttribute("compoundDetails", compoundService.findById(id));
    return "compound/compound-detail";
  }

}

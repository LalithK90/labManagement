package lk.GRILMSystem.labManagement.asset.compound.controller;

import lk.GRILMSystem.labManagement.asset.compound.service.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/compound")
@Controller
public class CompoundController {

    private final CompoundService compoundService;

    @Autowired
    public CompoundController (CompoundService compoundService){
        this.compoundService = compoundService;
    }

    @GetMapping("/compoundView")
    public String compoundView(){
        return"compound/compound";
    }

    //Send all employee data
    @RequestMapping
    public String compoundPage(Model model) {
        model.addAttribute("compounds", compoundService.findAll());
        return "compound/compound";
    }

}

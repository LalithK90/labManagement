package lk.GRILMSystem.labManagement.asset.mixer.controller;

import lk.GRILMSystem.labManagement.asset.LabTestParameter.entity.LabTestParameter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import lk.GRILMSystem.labManagement.asset.compound.entity.Specification;
import lk.GRILMSystem.labManagement.asset.mixer.entity.Mixer;
import lk.GRILMSystem.labManagement.asset.mixer.service.MixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/mixer")
@Controller
public class MixerController {

    private final MixerService mixerService;

    @Autowired
    public MixerController(MixerService mixerService) {
        this.mixerService = mixerService;
    }

    private String commonThing(Model model, Boolean booleanValue, Mixer mixer) {
        model.addAttribute("addStatus", booleanValue);
        model.addAttribute("mixer", mixer);
        return "mixer/addMixer";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("mixers", mixerService.findAll());
        return "mixer/mixer";
    }

    @GetMapping("/mixerView")
    public String mixerView() {
        return "mixer/mixer";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThing(model, false, new Mixer());
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("mixerDetail", mixerService.findById(id));
        return "mixer/mixer-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThing(model, true, mixerService.findById(id));
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid Mixer mixer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return commonThing(model, true, mixer);
        }
        redirectAttributes.addFlashAttribute("mixerDetail", mixerService.persist(mixer));
        return "redirect:/mixer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        mixerService.delete(id);
        return "redirect:/mixer";
    }
}

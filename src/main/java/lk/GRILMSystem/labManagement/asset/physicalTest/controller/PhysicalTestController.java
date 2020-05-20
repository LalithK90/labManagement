package lk.GRILMSystem.labManagement.asset.physicalTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/physicaltest")
@Controller
public class PhysicalTestController {

    @GetMapping("/parameters")
    public String getPhysicalTestParameters(){
        return "physicalTest/physicalTestParameters";
    }
}

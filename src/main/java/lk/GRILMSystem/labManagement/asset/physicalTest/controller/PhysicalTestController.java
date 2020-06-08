package lk.GRILMSystem.labManagement.asset.physicalTest.controller;

import lk.GRILMSystem.labManagement.asset.physicalTest.service.PhysicalTestParamService;
import lk.GRILMSystem.labManagement.asset.physicalTest.service.PhysicalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/physicaltest")
@Controller
public class PhysicalTestController {

    private  final PhysicalTestService physicalTestService;
    private final PhysicalTestParamService physicalTestParamService;

    @Autowired
    public PhysicalTestController(PhysicalTestService physicalTestService,
                                  PhysicalTestParamService physicalTestParamService){
        this.physicalTestParamService = physicalTestParamService;
        this.physicalTestService = physicalTestService;
    }

    //view parameters add page
    @GetMapping("/parameters")
    public String getPhysicalTestParameters(){
        return "physicalTestParameter/physicalTestParameters";
    }

   /* @PostMapping("/parameter/input")
    public String paramerInput(Model model){

    }*/

}

package lk.GRILMSystem.labManagement.asset.sampleReceiving.controller;

import lk.GRILMSystem.labManagement.asset.sampleReceiving.service.SampleReceivingLabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sampleReceivingLabTest")
@Controller
public class SampleReceivingLabTestController {
    private final SampleReceivingLabTestService sampleReceivingLabTestService;

    @Autowired
    public SampleReceivingLabTestController(SampleReceivingLabTestService sampleReceivingLabTestService){
        this.sampleReceivingLabTestService = sampleReceivingLabTestService;
    }
}

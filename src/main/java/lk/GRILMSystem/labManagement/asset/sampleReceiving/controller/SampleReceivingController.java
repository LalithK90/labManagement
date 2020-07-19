package lk.GRILMSystem.labManagement.asset.sampleReceiving.controller;

import lk.GRILMSystem.labManagement.asset.sampleReceiving.service.SampleReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sampleReceiving")
@Controller
public class SampleReceivingController {
    private final SampleReceivingService sampleReceivingService;

    @Autowired
    public SampleReceivingController(SampleReceivingService sampleReceivingService){
        this.sampleReceivingService = sampleReceivingService;
    }
}

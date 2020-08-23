package lk.GRILMSystem.labManagement.asset.payment.controller;

import lk.GRILMSystem.labManagement.asset.payment.entity.Enum.PaymentStatus;
import lk.GRILMSystem.labManagement.asset.payment.entity.Payment;
import lk.GRILMSystem.labManagement.asset.payment.service.PaymentService;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.service.SampleReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final SampleReceivingService sampleReceivingService;

    @Autowired
    public PaymentController(PaymentService paymentService, SampleReceivingService sampleReceivingService) {
        this.paymentService = paymentService;
        this.sampleReceivingService = sampleReceivingService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        return "payment/payment";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("addStatus", true);
        model.addAttribute("sampleReceivingList", sampleReceivingService.findAll());
        model.addAttribute("paymentStatuses", PaymentStatus.values());
        return "payment/addPayment";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("payment", paymentService.findById(id));
        model.addAttribute("addStatus", false);
        return "payment/addPayment";
    }

    @PostMapping(value = {"/add", "/update"})
    public String addComponent(@Valid @ModelAttribute Payment payment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("payment", payment);
            model.addAttribute("addStatus", true);
            return "payment/addPayment";
        }
        paymentService.persist(payment);
        return "redirect:/payment";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        paymentService.delete(id);
        return "redirect:/payment";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("paymentDetails", paymentService.findById(id));
        return "payment/payment-detail";
    }
}

package lk.lab_management.asset.payment.controller;

import lk.lab_management.asset.payment.entity.enums.PaymentStatus;
import lk.lab_management.asset.payment.entity.Payment;
import lk.lab_management.asset.payment.service.PaymentService;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingStatus;
import lk.lab_management.asset.sample_receiving.service.SampleReceivingService;
import lk.lab_management.util.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping( "/payment" )
public class PaymentController {

  private final PaymentService paymentService;
  private final SampleReceivingService sampleReceivingService;
  private final OperatorService operatorService;

  @Autowired
  public PaymentController(PaymentService paymentService, SampleReceivingService sampleReceivingService,
                           OperatorService operatorService) {
    this.paymentService = paymentService;
    this.sampleReceivingService = sampleReceivingService;
    this.operatorService = operatorService;
  }

  @GetMapping( "/needToPay" )
  public String needToPay(Model model) {
    List< SampleReceiving > sampleReceivings =
        sampleReceivingService.findBySampleReceivingStatus(SampleReceivingStatus.ACTIVE);
    sampleReceivings.addAll(sampleReceivingService.findBySampleReceivingStatus(SampleReceivingStatus.PPAID));
    model.addAttribute("addStatus", true);
    model.addAttribute("sampleReceivings", sampleReceivings);
    return "sampleReceiving/sampleReceiving";
  }

  @GetMapping
  public String findAll(Model model) {
    model.addAttribute("status", true);
    model.addAttribute("payments", paymentService.findAll());
    return "payment/payment";
  }

  @GetMapping( "/add/{id}" )
  public String form(@PathVariable Integer id, Model model) {
    model.addAttribute("payment", new Payment());
    model.addAttribute("addStatus", true);
    SampleReceiving sampleReceiving = sampleReceivingService.findById(id);
    List< Payment > payments = paymentService.findBySampleReceiving(sampleReceiving);
    if ( payments != null ) {
      BigDecimal paidAmount = BigDecimal.ZERO;
      for ( Payment paymentOne : payments ) {
        paidAmount = operatorService.addition(paidAmount, paymentOne.getAmount());
      }
      model.addAttribute("paidAmount", paidAmount);
    }
    model.addAttribute("payments", payments);
    model.addAttribute("sampleReceiving", sampleReceiving);
    model.addAttribute("paymentStatuses", PaymentStatus.values());
    return "payment/addPayment";
  }

  @GetMapping( "/edit/{id}" )
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("payment", paymentService.findById(id));
    model.addAttribute("addStatus", false);
    return "payment/addPayment";
  }

  @PostMapping( value = {"/add", "/update"} )
  public String addComponent(@Valid @ModelAttribute Payment payment, BindingResult result, Model model) {
    if ( result.hasErrors() ) {
      model.addAttribute("payment", payment);
      model.addAttribute("addStatus", true);
      return "payment/addPayment";
    }
    //sample receiving state to find total amount
    SampleReceiving sampleReceiving = sampleReceivingService.findById(payment.getSampleReceiving().getId());
    //if there is any previous payment
    List< Payment > payments = paymentService.findBySampleReceiving(sampleReceiving);

    if ( payments != null ) {
      BigDecimal paidAmount = BigDecimal.ZERO;
      for ( Payment paymentOne : payments ) {
        paidAmount = operatorService.addition(paidAmount, paymentOne.getAmount());
      }
      if ( sampleReceiving.getAmount().equals(paidAmount) ) {
        sampleReceiving.setSampleReceivingStatus(SampleReceivingStatus.PAID);
      }
    } else {
      if ( payment.getAmount().equals(sampleReceiving.getAmount()) ) {
        sampleReceiving.setSampleReceivingStatus(SampleReceivingStatus.PAID);
      } else {
        sampleReceiving.setSampleReceivingStatus(SampleReceivingStatus.PPAID);
      }

    }


    payment.setSampleReceiving(sampleReceiving);
    paymentService.persist(payment);
    return "redirect:/payment";
  }

  @GetMapping( "/delete/{id}" )
  public String delete(@PathVariable Integer id, Model model) {
    paymentService.delete(id);
    return "redirect:/payment";
  }

  @GetMapping( "/{id}" )
  public String view(@PathVariable Integer id, Model model) {
    Payment payment = paymentService.findById(id);
    model.addAttribute("paymentDetails", payment);
    model.addAttribute("sampleReceivingDetails", payment.getSampleReceiving());
    return "payment/payment-detail";
  }
}

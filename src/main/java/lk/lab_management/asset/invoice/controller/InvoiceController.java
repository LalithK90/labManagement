package lk.lab_management.asset.invoice.controller;



import lk.lab_management.asset.customer.service.CustomerService;
import lk.lab_management.asset.discount_ratio.service.DiscountRatioService;
import lk.lab_management.asset.invoice.entity.Invoice;
import lk.lab_management.asset.invoice.entity.enums.InvoicePrintOrNot;
import lk.lab_management.asset.invoice.entity.enums.InvoiceValidOrNot;
import lk.lab_management.asset.invoice.entity.enums.PaymentMethod;
import lk.lab_management.asset.invoice.service.InvoiceService;
import lk.lab_management.util.service.DateTimeAgeService;
import lk.lab_management.util.service.MakeAutoGenerateNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Controller
@RequestMapping( "/invoice" )
public class InvoiceController {
  private final InvoiceService invoiceService;
  private final CustomerService customerService;
  private final DateTimeAgeService dateTimeAgeService;
  private final DiscountRatioService discountRatioService;
  private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

  public InvoiceController(InvoiceService invoiceService, CustomerService customerService,
                           DateTimeAgeService dateTimeAgeService,
                           DiscountRatioService discountRatioService,
                           MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
    this.invoiceService = invoiceService;
    this.customerService = customerService;
    this.dateTimeAgeService = dateTimeAgeService;
    this.discountRatioService = discountRatioService;
    this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
  }

  @GetMapping
  public String invoice(Model model) {
    model.addAttribute("invoices",
            invoiceService.findAll());
    /*invoiceService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(dateTimeAgeService.getPastDateByMonth(3)), dateTimeAgeService.dateTimeToLocalDateEndInDay(LocalDate.now())));*/
    model.addAttribute("firstInvoiceMessage", true);
    return "invoice/invoice";
  }

  @GetMapping( "/search" )
  public String invoiceSearch(@RequestAttribute( "startDate" ) LocalDate startDate,
                              @RequestAttribute( "endDate" ) LocalDate endDate, Model model) {
    model.addAttribute("invoices",
            invoiceService.findByCreatedAtIsBetween(dateTimeAgeService.dateTimeToLocalDateStartInDay(startDate), dateTimeAgeService.dateTimeToLocalDateEndInDay(endDate)));
    model.addAttribute("firstInvoiceMessage", true);
    return "invoice/invoice";
  }

  private String common(Model model, Invoice invoice) {
    model.addAttribute("invoice", invoice);
    model.addAttribute("invoicePrintOrNots", InvoicePrintOrNot.values());
    model.addAttribute("paymentMethods", PaymentMethod.values());
    model.addAttribute("customers", customerService.findAll());
    model.addAttribute("discountRatios", discountRatioService.findAll());
    return "invoice/addInvoice";
  }

  @GetMapping( "/add" )
  public String getInvoiceForm(Model model) {
    return common(model, new Invoice());
  }

  @GetMapping( "/{id}" )
  public String viewDetails(@PathVariable Integer id, Model model) {
    Invoice invoice = invoiceService.findById(id);
    model.addAttribute("invoiceDetail", invoice);
    model.addAttribute("customerDetail", invoice.getCustomer());
    return "invoice/invoice-detail";
  }

  @PostMapping
  public String persistInvoice(@Valid @ModelAttribute Invoice invoice, BindingResult bindingResult, Model model) {
    if ( bindingResult.hasErrors() ) {
      return common(model, invoice);
    }
    if ( invoice.getId() == null ) {
      if ( invoiceService.findByLastInvoice() == null ) {
        //need to generate new one
        invoice.setCode("JNSI" + makeAutoGenerateNumberService.numberAutoGen(null).toString());
      } else {
        System.out.println("last customer not null");
        //if there is customer in db need to get that customer's code and increase its value
        String previousCode = invoiceService.findByLastInvoice().getCode().substring(4);
        invoice.setCode("JNSI" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
      }
    }
    invoice.setInvoiceValidOrNot(InvoiceValidOrNot.VALID);
    invoiceService.persist(invoice);
    //todo - if invoice is required needed to send pdf to backend

    return "redirect:/invoice/add";
  }


  @GetMapping( "/remove/{id}" )
  public String removeInvoice(@PathVariable( "id" ) Integer id) {
    Invoice invoice = invoiceService.findById(id);
    invoice.setInvoiceValidOrNot(InvoiceValidOrNot.NOTVALID);
    invoiceService.persist(invoice);
    return "redirect:/invoice";
  }
}

package lk.lab_management.asset.customer.controller;


import lk.lab_management.asset.common_asset.model.Enum.Title;
import lk.lab_management.asset.customer.entity.Customer;
import lk.lab_management.asset.customer.entity.Enum.CustomerType;
import lk.lab_management.asset.customer.service.CustomerService;
import lk.lab_management.util.interfaces.AbstractController;
import lk.lab_management.util.service.EmailService;
import lk.lab_management.util.service.MakeAutoGenerateNumberService;
import lk.lab_management.util.service.TwilioMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController implements AbstractController<Customer, Integer> {
    private final CustomerService customerService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;
    private final EmailService emailService;
    private final TwilioMessageService twilioMessageService;

    public CustomerController(CustomerService customerService, MakeAutoGenerateNumberService makeAutoGenerateNumberService, EmailService emailService, TwilioMessageService twilioMessageService) {
        this.customerService = customerService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
        this.emailService = emailService;
        this.twilioMessageService = twilioMessageService;
    }


    private String commonThings(Model model, Customer customer, Boolean addState) {
        model.addAttribute("title", Title.values());
        model.addAttribute("customer", customer);
        model.addAttribute("addStatus", addState);
        model.addAttribute("customerType", CustomerType.values());
        return "customer/addCustomer";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customer/customer";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Customer(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return commonThings(model, customer, true);
        }
//phone number length validator
        if (customer.getMobile() != null) {
            customer.setMobile(makeAutoGenerateNumberService.phoneNumberLengthValidator(customer.getMobile()));
        }

//if customer has id that customer is not a new customer
        if (customer.getId() == null) {
            //if there is not customer in db
            if (customerService.lastCustomer() == null) {
                System.out.println("last customer null");
                //need to generate new onecustomer
                customer.setCode("GRI"+makeAutoGenerateNumberService.numberAutoGen(null).toString());
            } else {
                System.out.println("last customer not null");
                //if there is customer in db need to get that customer's code and increase its value
                String previousCode = customerService.lastCustomer().getCode().substring(3);
                customer.setCode("GRI"+makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
            }
            //send welcome message and email
            if (customer.getEmail() != null) {
                //  emailService.sendEmail(customer.getEmail(), "Welcome Message", "Welcome to Kmart Super...");
            }
            if (customer.getMobile() != null) {
                //    twilioMessageService.sendSMS(customer.getMobile(), "Welcome to Kmart Super");
            }
        }

        redirectAttributes.addFlashAttribute("customerDetail", customerService.persist(customer));
        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, customerService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("customerDetail", customerService.findById(id));
        return "customer/customer-detail";
    }
}

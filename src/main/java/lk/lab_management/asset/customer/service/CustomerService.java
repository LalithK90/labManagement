package lk.lab_management.asset.customer.service;

import lk.lab_management.asset.customer.dao.CustomerDao;
import lk.lab_management.asset.customer.entity.Customer;
import lk.lab_management.util.interfaces.AbstractService;
import lk.lab_management.util.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig( cacheNames = "customer" )
public class CustomerService implements AbstractService<Customer, Integer> {
    private final CustomerDao customerDao;
    private final EmailService emailService;

    @Autowired
    public CustomerService(CustomerDao customerDao, EmailService emailService) {
        this.customerDao = customerDao;
        this.emailService = emailService;
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public Customer findById(Integer id) {
        return customerDao.getOne(id);
    }

    public Customer persist(Customer customer) {
        Customer customer1 = customerDao.save(customer);
        if(customer1.getEmail()!=null){
            String message = "Dear "+customer.getName()+
                    "\n Code    :"+customer.getCode()+
                    "\n Customer name   :"+customer.getName()+
                    "\n Mobile  :"+customer.getMobile()+
                    "\n Address :"+customer.getAddress()+
                    "\n Company :"+customer.getCompanyName()+
                    "\n NIC     :"+customer.getNic();
            emailService.sendEmail(customer1.getEmail(), "Welcome to GRI", message);
        }
        return customer1;
    }

    public boolean delete(Integer id) {
        customerDao.deleteById(id);
        return false;
    }

    public List<Customer> search(Customer customer) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> customerExample = Example.of(customer, matcher);
        return customerDao.findAll(customerExample);
    }

    public Customer lastCustomer(){
        return customerDao.findFirstByOrderByIdDesc();
    }
}

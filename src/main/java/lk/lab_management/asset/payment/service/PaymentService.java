package lk.lab_management.asset.payment.service;

import lk.lab_management.asset.payment.entity.Payment;
import lk.lab_management.asset.payment.dao.PaymentDao;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.util.interfaces.AbstractService;
import lk.lab_management.util.service.MakeAutoGenerateNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements AbstractService<Payment, Integer> {

    private final PaymentDao paymentDao;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    @Autowired
    public PaymentService(PaymentDao paymentDao, MakeAutoGenerateNumberService makeAutoGenerateNumberService){
        this.paymentDao = paymentDao;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }

    @Cacheable
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }


    public Payment findById(Integer id) {
        return paymentDao.getOne(id);
    }


    public Payment persist(Payment payment) {
        if(payment.getId()==null){
            //if there is not customer in db
            if (paymentDao.findFirstByOrderByIdDesc() == null) {
                System.out.println("last customer null");
                //need to generate new onecustomer
                payment.setNumber("GRIP"+makeAutoGenerateNumberService.numberAutoGen(null).toString());
            } else {
                System.out.println("last customer not null");
                //if there is customer in db need to get that customer's code and increase its value
                String previousCode = paymentDao.findFirstByOrderByIdDesc().getNumber().substring(4);
                payment.setNumber("GRIP"+makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
            }
        }
        return paymentDao.save(payment);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        paymentDao.deleteById(id);
        return false;
    }

    @Cacheable
    public List<Payment> search(Payment payment) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Payment> paymentExample = Example.of(payment,matcher);
        return paymentDao.findAll(paymentExample);
    }

  public List< Payment> findBySampleReceiving(SampleReceiving sampleReceiving) {
  return paymentDao.findBySampleReceiving(sampleReceiving);
    }
}

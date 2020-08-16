package lk.GRILMSystem.labManagement.asset.payment.service;

import lk.GRILMSystem.labManagement.asset.payment.entity.Payment;
import lk.GRILMSystem.labManagement.asset.payment.dao.PaymentDao;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
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

    @Autowired
    public PaymentService(PaymentDao paymentDao){
        this.paymentDao = paymentDao;
    }

    @Cacheable
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }


    public Payment findById(Integer id) {
        return paymentDao.getOne(id);
    }


    public Payment persist(Payment payment) {
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
}

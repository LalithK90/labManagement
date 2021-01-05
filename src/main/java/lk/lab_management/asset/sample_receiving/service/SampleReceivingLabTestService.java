package lk.lab_management.asset.sample_receiving.service;

import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.customer.entity.Customer;
import lk.lab_management.asset.sample_receiving.dao.SampleReceivingLabTestDao;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTestResult;
import lk.lab_management.asset.sample_receiving.entity.enums.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingLabTestStatus;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import lk.lab_management.util.interfaces.AbstractService;
import lk.lab_management.util.service.EmailService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleReceivingLabTestService implements AbstractService<SampleReceivingLabTest, Integer> {
    private final SampleReceivingLabTestDao sampleReceivingLabTestDao;
    private final EmailService emailService;

    public SampleReceivingLabTestService(SampleReceivingLabTestDao sampleReceivingLabTestDao, EmailService emailService){
        this.sampleReceivingLabTestDao = sampleReceivingLabTestDao;
        this.emailService = emailService;
    }

    @Override
    public Object findAll() {
        return sampleReceivingLabTestDao.findAll();
    }

    @Override
    public SampleReceivingLabTest findById(Integer id) {
        return sampleReceivingLabTestDao.getOne(id);
    }

    @Override
    public SampleReceivingLabTest persist(SampleReceivingLabTest sampleReceivingLabTest) {
        SampleReceivingLabTest sampleReceivingLabTest1 = sampleReceivingLabTestDao.save(sampleReceivingLabTest);
        Customer customer = sampleReceivingLabTest1.getSampleReceiving().getCustomer();
        if(customer.getEmail()!=null){
            List<SampleReceivingLabTestResult> sampleReceivingLabTestResultList = sampleReceivingLabTest1.getSampleReceivingLabTestResults();
            // TODO: 12/28/2020 display the results list
            //if(status = RESULTENTER) else if(status = NOTRESULTENTER)
            String message = "Dear "+customer.getName()+
                    "\n Results have been entered for the test"+sampleReceivingLabTest1.getLabTestName().toString();
            emailService.sendEmail(customer.getEmail(), "Test Results", message);
        }
        return sampleReceivingLabTest1;
    }

    @Override
    public boolean delete(Integer id) {
        sampleReceivingLabTestDao.deleteById(id);
        return false;
    }

    @Override
    public List<SampleReceivingLabTest> search(SampleReceivingLabTest sampleReceivingLabTest) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<SampleReceivingLabTest> sampleReceivingLabTestExample = Example.of(sampleReceivingLabTest, matcher);
        return sampleReceivingLabTestDao.findAll(sampleReceivingLabTestExample);
    }

    public List<SampleReceivingLabTest> findByLabTestNameAndAcceptability(LabTestName labTestName, Acceptability acceptability) {
       return sampleReceivingLabTestDao.findByLabTestNameAndAcceptability(labTestName, acceptability);
    }

    public List<SampleReceivingLabTest> findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus(LabTestName labTestName, Acceptability acceptability, SampleReceivingLabTestStatus sampleReceivingLabTestStatus) {
    return sampleReceivingLabTestDao.findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus(labTestName, acceptability, sampleReceivingLabTestStatus);
    }
}

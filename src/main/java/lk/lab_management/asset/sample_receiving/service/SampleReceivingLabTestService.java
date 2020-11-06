package lk.lab_management.asset.sample_receiving.service;

import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.sample_receiving.dao.SampleReceivingLabTestDao;
import lk.lab_management.asset.sample_receiving.entity.enums.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingLabTestStatus;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import lk.lab_management.util.interfaces.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleReceivingLabTestService implements AbstractService<SampleReceivingLabTest, Integer> {
    private final SampleReceivingLabTestDao sampleReceivingLabTestDao;

    public SampleReceivingLabTestService(SampleReceivingLabTestDao sampleReceivingLabTestDao){
        this.sampleReceivingLabTestDao = sampleReceivingLabTestDao;
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
        return sampleReceivingLabTestDao.save(sampleReceivingLabTest);
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

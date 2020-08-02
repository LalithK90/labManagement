package lk.GRILMSystem.labManagement.asset.sampleReceiving.service;

import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.LabTestName;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.dao.SampleReceivingLabTestDao;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.Acceptability;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.SampleReceivingLabTestStatus;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTest;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
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

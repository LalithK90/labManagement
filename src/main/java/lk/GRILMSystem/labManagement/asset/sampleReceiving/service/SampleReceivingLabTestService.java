package lk.GRILMSystem.labManagement.asset.sampleReceiving.service;

import lk.GRILMSystem.labManagement.asset.labTest.entity.LabTest;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.dao.SampleReceivingLabTestDao;
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
}

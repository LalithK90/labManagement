package lk.GRILMSystem.labManagement.asset.sampleReceiving.service;

import lk.GRILMSystem.labManagement.asset.sampleReceiving.dao.SampleReceivingDao;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceiving;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleReceivingService implements AbstractService<SampleReceiving,Integer> {
    private final SampleReceivingDao sampleReceivingDao;

    public SampleReceivingService(SampleReceivingDao sampleReceivingDao){
        this.sampleReceivingDao = sampleReceivingDao;
    }

    @Override
    public Object findAll() {
        return sampleReceivingDao.findAll();
    }

    @Override
    public SampleReceiving findById(Integer id) {
        return sampleReceivingDao.getOne(id);
    }

    @Override
    public SampleReceiving persist(SampleReceiving sampleReceiving) {
        return sampleReceivingDao.save(sampleReceiving);
    }

    @Override
    public boolean delete(Integer id) {
        sampleReceivingDao.deleteById(id);
        return false;
    }

    @Override
    public List<SampleReceiving> search(SampleReceiving sampleReceiving) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<SampleReceiving> sampleReceivingExample = Example.of(sampleReceiving, matcher);
        return sampleReceivingDao.findAll(sampleReceivingExample);
    }
}

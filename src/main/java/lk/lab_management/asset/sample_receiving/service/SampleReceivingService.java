package lk.lab_management.asset.sample_receiving.service;

import lk.lab_management.asset.sample_receiving.dao.SampleReceivingDao;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingStatus;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.util.interfaces.AbstractService;
import lk.lab_management.util.service.MakeAutoGenerateNumberService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleReceivingService implements AbstractService<SampleReceiving, Integer> {
    private final SampleReceivingDao sampleReceivingDao;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;

    public SampleReceivingService(SampleReceivingDao sampleReceivingDao,
                                  MakeAutoGenerateNumberService makeAutoGenerateNumberService) {
        this.sampleReceivingDao = sampleReceivingDao;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    }


    public Object findAll() {
        return sampleReceivingDao.findAll();
    }


    public SampleReceiving findById(Integer id) {
        return sampleReceivingDao.getOne(id);
    }


    public SampleReceiving persist(SampleReceiving sampleReceiving) {
        if (sampleReceiving.getId() == null) {
            sampleReceiving.setSampleReceivingStatus(SampleReceivingStatus.ACTIVE);
            if (sampleReceivingDao.findFirstByOrderByIdDesc() == null) {
                sampleReceiving.setNumber("GRIR"+makeAutoGenerateNumberService.numberAutoGen(null).toString());
            } else {
                String previousCode = sampleReceivingDao.findFirstByOrderByIdDesc().getNumber().substring(4);
                sampleReceiving.setNumber("GRIR"+makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
            }
        }
        return sampleReceivingDao.save(sampleReceiving);
    }


    public boolean delete(Integer id) {
        sampleReceivingDao.deleteById(id);
        return false;
    }


    public List<SampleReceiving> search(SampleReceiving sampleReceiving) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<SampleReceiving> sampleReceivingExample = Example.of(sampleReceiving, matcher);
        return sampleReceivingDao.findAll(sampleReceivingExample);
    }

  public List<SampleReceiving> findBySampleReceivingStatus(SampleReceivingStatus sampleReceivingStatus) {
        return sampleReceivingDao.findBySampleReceivingStatus(sampleReceivingStatus);
    }
}

package lk.GRILMSystem.labManagement.asset.labTest.service;


import lk.GRILMSystem.labManagement.asset.labTest.dao.LabTestDao;
import lk.GRILMSystem.labManagement.asset.labTest.entity.LabTest;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTestService implements AbstractService<LabTest, Integer> {
    private final LabTestDao labTestDao;

    public LabTestService(LabTestDao labTestDao) {
        this.labTestDao = labTestDao;
    }

    public List<LabTest> findAll() {
        return labTestDao.findAll();
    }

    public LabTest findById(Integer id) {
        return labTestDao.getOne(id);
    }

    public LabTest persist(LabTest labTest) {
        return labTestDao.save(labTest);
    }

    public boolean delete(Integer id) {
        labTestDao.deleteById(id);
        return true;
    }

    public List<LabTest> search(LabTest labTest) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<LabTest> labTestExample = Example.of(labTest, matcher);
        return labTestDao.findAll(labTestExample);
    }
}

package lk.GRILMSystem.labManagement.asset.LabTestParameter.service;


import lk.GRILMSystem.labManagement.asset.LabTestParameter.dao.LabTestParameterDao;
import lk.GRILMSystem.labManagement.asset.LabTestParameter.entity.LabTestParameter;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTestParameterService implements AbstractService<LabTestParameter, Integer> {
private final LabTestParameterDao labTestParameterDao;

    public LabTestParameterService(LabTestParameterDao labTestParameterDao) {
        this.labTestParameterDao = labTestParameterDao;
    }

    public List<LabTestParameter> findAll() {
        return labTestParameterDao.findAll();
    }

    public LabTestParameter findById(Integer id) {
        return labTestParameterDao.getOne(id);
    }

    public LabTestParameter persist(LabTestParameter labTestParameter) {
        return labTestParameterDao.save(labTestParameter);
    }

    public boolean delete(Integer id) {
        labTestParameterDao.deleteById(id);
        return true;
    }

    public List<LabTestParameter> search(LabTestParameter labTestParameter) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<LabTestParameter> labTestParameterExample = Example.of(labTestParameter, matcher);
        return labTestParameterDao.findAll(labTestParameterExample);
    }
}

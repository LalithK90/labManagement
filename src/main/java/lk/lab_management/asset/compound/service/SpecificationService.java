package lk.lab_management.asset.compound.service;

import lk.lab_management.asset.compound.dao.SpecificationDao;
import lk.lab_management.asset.compound.entity.Compound;
import lk.lab_management.asset.compound.entity.Specification;
import lk.lab_management.asset.compound.entity.Enum.LabTestName;
import lk.lab_management.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService implements AbstractService<Specification,Integer> {

    private final SpecificationDao specificationDao;

    @Autowired
    public SpecificationService(SpecificationDao specificationDao){
        this.specificationDao = specificationDao;
    }

    @Cacheable
    public List<Specification> findAll() {
        return specificationDao.findAll();
    }


    public Specification findById(Integer id) {
        return specificationDao.getOne(id);
    }


    public Specification persist(Specification compound) {
        return specificationDao.save(compound);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        specificationDao.deleteById(id);
        return false;
    }

    @Cacheable
    public List<Specification> search(Specification compound) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Specification> compoundExample = Example.of(compound,matcher);
        return specificationDao.findAll(compoundExample);
    }

    public List<Specification> findByLabTestNameAndCompound(LabTestName labTestName, Compound compound) {
   return specificationDao.findByLabTestNameAndCompound(labTestName, compound);
    }
}

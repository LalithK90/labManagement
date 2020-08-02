package lk.GRILMSystem.labManagement.asset.compound.service;

import lk.GRILMSystem.labManagement.asset.compound.dao.CompoundDao;
import lk.GRILMSystem.labManagement.asset.compound.dao.SpecificationDao;
import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompoundService implements AbstractService<Compound,Integer> {

    private final CompoundDao compoundDao;

    @Autowired
    public CompoundService(CompoundDao compoundDao){
        this.compoundDao = compoundDao;
    }

    @Cacheable
    public List<Compound> findAll() {
        return compoundDao.findAll();
    }


    public Compound findById(Integer id) {
        return compoundDao.getOne(id);
    }


    public Compound persist(Compound compound) {
        return compoundDao.save(compound);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        compoundDao.deleteById(id);
        return false;
    }

    @Cacheable
    public List<Compound> search(Compound compound) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Compound> compoundExample = Example.of(compound,matcher);
        return compoundDao.findAll(compoundExample);
    }
}

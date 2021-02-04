package lk.lab_management.asset.compound.service;

import lk.lab_management.asset.common_asset.model.enums.LiveDead;
import lk.lab_management.asset.compound.dao.CompoundDao;
import lk.lab_management.asset.compound.entity.Compound;
import lk.lab_management.asset.employee.entity.Employee;
import lk.lab_management.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompoundService implements AbstractService<Compound,Integer> {

    private final CompoundDao compoundDao;

    @Autowired
    public CompoundService(CompoundDao compoundDao){
        this.compoundDao = compoundDao;
    }

    @Cacheable
    public List<Compound> findAll() {
        return compoundDao.findAll().stream()
                .filter(x -> LiveDead.ACTIVE.equals(x.getLiveDead()))
                .collect(Collectors.toList());
    }


    public Compound findById(Integer id) {
        return compoundDao.getOne(id);
    }


    public Compound persist(Compound compound) {
        //set status to LiveDead.ACTIVE while saving
        if(compound.getId()==null){
            compound.setLiveDead(LiveDead.ACTIVE);}
        return compoundDao.save(compound);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        //change status to LiveDead.STOP
        Compound compound = compoundDao.getOne(id);
        compound.setLiveDead(LiveDead.STOP);
        compoundDao.save(compound);
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

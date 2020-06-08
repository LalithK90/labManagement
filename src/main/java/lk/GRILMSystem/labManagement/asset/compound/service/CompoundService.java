package lk.GRILMSystem.labManagement.asset.compound.service;

import lk.GRILMSystem.labManagement.asset.compound.dao.CompoundDao;
import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Override
    public Compound findById(Integer id) {
        return null;
    }

    @Override
    public Compound persist(Compound compound) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<Compound> search(Compound compound) {
        return null;
    }
}

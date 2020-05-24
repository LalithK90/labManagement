package lk.GRILMSystem.labManagement.asset.physicalTest.service;

import lk.GRILMSystem.labManagement.asset.physicalTest.dao.PhysicalTestDao;
import lk.GRILMSystem.labManagement.asset.physicalTest.entity.PhysicalTest;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "physicalTest")
public class PhysicalTestService  {
    private final PhysicalTestDao physicalTestDao;

    @Autowired
    public PhysicalTestService(PhysicalTestDao physicalTestDao){
        this.physicalTestDao = physicalTestDao;
    }


    @Cacheable
    public List<PhysicalTestDao> findAll() {
        return null;
    }


    @Cacheable
    public PhysicalTest findById(Integer id) {
        return physicalTestDao.getOne(id);
    }

    @Caching( evict = {@CacheEvict( value = "physicalTest", allEntries = true )},
            put = {@CachePut( value = "physicalTest", key = "#physicalTest.id" )} )
    @Transactional
    public PhysicalTest persist(PhysicalTest physicalTest) {
        return physicalTestDao.save(physicalTest);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        physicalTestDao.deleteById(id);
        return false;
    }

    @Cacheable
    public List<PhysicalTestDao> search(PhysicalTestDao physicalTestDao) {
        return null;
    }

    @Cacheable
    public List<PhysicalTest> search(PhysicalTest physicalTest) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<PhysicalTest> physicalTestExample = Example.of(physicalTest, matcher);
        return physicalTestDao.findAll(physicalTestExample);
    }
}

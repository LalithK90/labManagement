package lk.GRILMSystem.labManagement.asset.mixer.service;

import lk.GRILMSystem.labManagement.asset.mixer.dao.MixerDao;
import lk.GRILMSystem.labManagement.asset.mixer.entity.Mixer;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "mixer")
public class MixerService implements AbstractService<Mixer, Integer> {

    private final MixerDao mixerDao;

    @Autowired
    public MixerService(MixerDao mixerDao){this.mixerDao = mixerDao;}


    @Cacheable
    public List<Mixer> findAll() {
        return mixerDao.findAll();
    }

    @Cacheable
    public Mixer findById(Integer id) {
        return mixerDao.getOne(id);
    }

    @Caching( evict = {@CacheEvict( value = "mixer", allEntries = true )},
            put = {@CachePut( value = "mixer", key = "#mixer.id" )} )
    @Transactional
    public Mixer persist(Mixer mixer) {
        return mixerDao.save(mixer);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        mixerDao.deleteById(id);
        return false;
    }

    @Cacheable
    public List<Mixer> search(Mixer mixer) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Mixer> mixerExample = Example.of(mixer, matcher);
        return mixerDao.findAll(mixerExample);
    }
}

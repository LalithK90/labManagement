package lk.lab_management.asset.message.service;

import lk.lab_management.asset.message.dao.EmailMessageDao;
import lk.lab_management.asset.message.entity.EmailMessage;
import lk.lab_management.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig( cacheNames = "emailMessage" )
public class EmailMessageService implements AbstractService<EmailMessage, Integer > {
    private final EmailMessageDao emailMessageDao;

    @Autowired
    public EmailMessageService(EmailMessageDao emailMessageDao) {
        this.emailMessageDao = emailMessageDao;
    }

    @Override
    @Cacheable
    public List< EmailMessage > findAll() {
        return emailMessageDao.findAll();
    }

    @Override
    @Cacheable
    public EmailMessage findById(Integer id) {
        return emailMessageDao.getOne(id);
    }

    @Override
    @Caching( evict = {@CacheEvict( value = "emailMessage", allEntries = true )},
            put = {@CachePut( value = "emailMessage", key = "#emailMessage.id" )} )
    public EmailMessage persist(EmailMessage emailMessage) {
        return emailMessageDao.save(emailMessage);
    }

    @Override
    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List< EmailMessage > search(EmailMessage emailMessage) {
        return null;
    }
}

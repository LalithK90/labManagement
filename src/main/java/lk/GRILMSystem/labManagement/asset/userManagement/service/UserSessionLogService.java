package lk.GRILMSystem.labManagement.asset.userManagement.service;

import lk.GRILMSystem.labManagement.asset.userManagement.dao.UserSessionLogDao;
import lk.GRILMSystem.labManagement.asset.userManagement.entity.Enum.UserSessionLogStatus;
import lk.GRILMSystem.labManagement.asset.userManagement.entity.User;
import lk.GRILMSystem.labManagement.asset.userManagement.entity.UserSessionLog;
import lk.GRILMSystem.labManagement.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static lk.GRILMSystem.labManagement.asset.userManagement.entity.Enum.UserSessionLogStatus.LOGGED;

@Service
@CacheConfig( cacheNames = {"userSessionLog"} )
public class UserSessionLogService implements AbstractService<UserSessionLog, Integer > {
    private final UserSessionLogDao userSessionLogDao;

    @Autowired
    public UserSessionLogService(UserSessionLogDao userSessionLogDao) {
        this.userSessionLogDao = userSessionLogDao;
    }

    @Override
    @Cacheable
    public List< UserSessionLog > findAll() {
        return userSessionLogDao.findAll();
    }

    @Override
    @Cacheable
    public UserSessionLog findById(Integer id) {
        return userSessionLogDao.getOne(id);
    }

    @Override
    @Caching( evict = {@CacheEvict( value = "userSessionLog", allEntries = true )},
            put = {@CachePut( value = "userSessionLog", key = "#userSessionLog.id" )} )
    public UserSessionLog persist(UserSessionLog userSessionLog) {
        return userSessionLogDao.save(userSessionLog);
    }

    @Override
    public boolean delete(Integer id) {
        // can not be implement

        return true;
    }

    public void delete(UserSessionLog userSessionLog){
         userSessionLogDao.delete(userSessionLog);
    }

    @Override
    public List< UserSessionLog > search(UserSessionLog userSessionLog) {
        return null;
    }

    @Cacheable
    public UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus) {
        return userSessionLogDao.findByUserAndUserSessionLogStatus(user, userSessionLogStatus);
    }

    //find the logged in user
    public String findByUserSessionLogStatus(){
        UserSessionLog userSessionLog = userSessionLogDao.findTopByUserSessionLogStatusOrderByIdDesc(LOGGED);
        User user = userSessionLog.getUser();
        String userName = user.getUsername();
        System.out.println(userName);
        return userName;
    }
}

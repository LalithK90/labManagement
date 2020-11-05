package lk.lab_management.asset.user_management.dao;

import lk.lab_management.asset.user_management.entity.enums.UserSessionLogStatus;
import lk.lab_management.asset.user_management.entity.User;
import lk.lab_management.asset.user_management.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository<UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
    UserSessionLog findTopByUserSessionLogStatusOrderByIdDesc(UserSessionLogStatus userSessionLogStatus);
}

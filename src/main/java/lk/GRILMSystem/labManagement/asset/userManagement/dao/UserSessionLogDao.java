package lk.GRILMSystem.labManagement.asset.userManagement.dao;

import lk.GRILMSystem.labManagement.asset.userManagement.entity.Enum.UserSessionLogStatus;
import lk.GRILMSystem.labManagement.asset.userManagement.entity.User;
import lk.GRILMSystem.labManagement.asset.userManagement.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository<UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
    UserSessionLog findTopByUserSessionLogStatusOrderByIdDesc(UserSessionLogStatus userSessionLogStatus);
}

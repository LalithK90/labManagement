package lk.lab_management.asset.user_management.dao;

import lk.lab_management.asset.common_asset.model.enums.LiveDead;
import lk.lab_management.asset.employee.entity.Employee;
import lk.lab_management.asset.user_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository< User, Integer > {

    @Query( value = "select id from User where employee_id=?1", nativeQuery = true )
    Integer findByEmployeeId(@Param("employee_id") Integer id);

    @Query( "select id from User where username=?1" )
    Integer findUserIdByUserName(String userName);

    User findByUsername(String name);

    User findByEmployee(Employee employee);

    List<User> findByLiveDead(LiveDead live_dead);

   }

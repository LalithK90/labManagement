package lk.lab_management.asset.payment.dao;

import lk.lab_management.asset.payment.entity.Payment;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
  Payment findFirstByOrderByIdDesc();
  List< Payment> findBySampleReceiving(SampleReceiving sampleReceiving);

  List< Payment> findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to);

  List< Payment> findByCreatedAtIsBetweenAndCreatedBy(LocalDateTime from, LocalDateTime to, String userName);
}

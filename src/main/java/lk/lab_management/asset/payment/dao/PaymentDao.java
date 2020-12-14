package lk.lab_management.asset.payment.dao;

import lk.lab_management.asset.payment.entity.Payment;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
  List< Payment> findBySampleReceiving(SampleReceiving sampleReceiving);
}

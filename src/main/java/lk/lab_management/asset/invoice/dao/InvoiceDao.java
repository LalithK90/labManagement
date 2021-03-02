package lk.lab_management.asset.invoice.dao;


import lk.lab_management.asset.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer > {
    List< Invoice > findByCreatedAtIsBetween(LocalDateTime form, LocalDateTime to);

    List< Invoice > findByCreatedAtIsBetweenAndCreatedBy(LocalDateTime form, LocalDateTime to, String username);

    Invoice findFirstByOrderByIdDesc();
}

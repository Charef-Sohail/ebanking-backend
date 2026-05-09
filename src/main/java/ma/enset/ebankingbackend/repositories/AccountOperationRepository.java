package ma.enset.ebankingbackend.repositories;


import ma.enset.ebankingbackend.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findByBankAccount_Id(String accountId);
    Page<AccountOperation> findByBankAccount_Id(String accountId, Pageable pageable);

}

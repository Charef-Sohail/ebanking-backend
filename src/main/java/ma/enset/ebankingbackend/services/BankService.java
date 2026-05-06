package ma.enset.ebankingbackend.services;

import jakarta.transaction.Transactional;
import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository  bankAccountRepository;
    public void consulter() {
        BankAccount bankAccount =
                bankAccountRepository.findById("501bde33-cf90-4db4-8dea-2d845d0bed09").orElse(null);
        System.out.println("******************************************");
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getCustomer().getName());
        if (bankAccount instanceof CurrentAccount) {

        } else if (bankAccount instanceof SavingAccount) {

        }
        bankAccount.getAccountOperations().forEach(op -> {
                    System.out.println(op.getType() + "\t" + op.getAmount() + "\t" + op.getOperationDate());
                }

        );
    }

}

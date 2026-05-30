package ma.enset.ebankingbackend.services;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class BankAccountToolService {

    private final BankAccountService bankAccountService;

    public BankAccountToolService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    // Cette méthode devient un outil pour l'IA
    @Tool(description = "Récupère le solde d'un compte bancaire à partir de son ID")
    public String getBalance(String accountId) {
        try {
            return "Le solde du compte " + accountId + " est de " +
                    bankAccountService.getBankAccount(accountId).getBalance() + " MAD.";
        } catch (Exception e) {
            return "Compte non trouvé.";
        }
    }
}
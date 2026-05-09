package ma.enset.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.CustomerDTO;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbackend.repositories.CustomerRepository;
import ma.enset.ebankingbackend.services.BankAccountService;
import ma.enset.ebankingbackend.services.BankAccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private final CustomerRepository customerRepository;
    private BankAccountService bankAccountService;

    @GetMapping("customers")
    public List<CustomerDTO> getCustomers() {
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO request) {
       return bankAccountService.saveCustomer(request);
    }

    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable(name="id") Long customerId,
                                      @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable(name="id") Long customerId) {
        bankAccountService.deleteCustomer(customerId);
    }



}

package com.fasb.api;


import com.fasb.model.Account;
import com.fasb.model.Credit;
import com.fasb.model.Customer;
import com.fasb.service.AccountService;
import com.fasb.service.CreditService;
import com.fasb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @Autowired
    AccountService accountService;

    @Autowired
    CreditService creditService;

    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam(required = false) String lastName, @RequestParam(required = false, defaultValue = "firstName") String sortBy, @RequestParam(defaultValue = "asc", required = false) String sortDir){
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if(sortDir.equals("desc")){
            sortDirection = Sort.Direction.DESC;
        }
        if(lastName != null){
            return customerService.getCustomersByLastName(lastName,sortBy, sortDirection);
        }else{
            return customerService.getCustomers(sortBy, sortDirection);
        }
    }



    @PostMapping(value = "/create")
    @Transactional
    public Customer create(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return customer;
    }

    @Transactional
    @PostMapping(value = "/createCredit/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Credit createCredit(@PathVariable("customerID") final int customerID, @RequestBody Credit submittedCredit){
        Customer customer = customerService.getCustomerById(customerID);
        LocalDateTime exprieDate = LocalDateTime.now();

        Credit credit = new Credit(
                submittedCredit.getOriginalTerm(),
                submittedCredit.getRemainingTerm(),
                submittedCredit.getOriginalCreditAmount(),
                submittedCredit.getRemainingCreditAmount(),
                customer,
                exprieDate.plusMonths(submittedCredit.getOriginalTerm()));


        creditService.createCredit(credit);
        return credit;
    }


    @Transactional
    @PostMapping(value = "/createAccount/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(@PathVariable("customerID") final int customerID, @RequestBody Account submittedAccount){
        Account account = new Account();
        Customer customer = customerService.getCustomerById(customerID);
        account.setBalance(submittedAccount.getBalance());
        account.setCustomer(customer);
        accountService.createAccount(account);
        return account;
    }


    @Transactional
    @GetMapping(value = "/balance/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getBalanceForCustomer(int customerID){
        //TODO REFACTOR AND MOVE TO SERVICE
        List<Credit> credits = creditService.getCreditsByCustomerId(customerID);
        List<Account> accounts = accountService.getAccountsByCustomerId(customerID);
        Long sumOfAllCredits = credits.stream().collect(Collectors.summingLong(o -> o.getRemainingCreditAmount()));
        Long totalSumOnAllAccounts = accounts.stream().collect(Collectors.summingLong(o -> o.getBalance()));
        return totalSumOnAllAccounts - sumOfAllCredits;
    }
    @Transactional
    @GetMapping(value = "/balance/institution", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getBalanceForInstitution(){
        //TODO REFACTOR MOVE TO GENERIC OR INSTITUTION CONTROLLER AND MOVE LOGIC FOR SUM IN SERVICE
        List<Credit> credits = creditService.getCredits();
        List<Account> accounts = accountService.getAccounts();
        Long sumOfAllCredits = credits.stream().collect(Collectors.summingLong(o -> o.getRemainingCreditAmount()));
        Long totalSumOnAllAccounts = accounts.stream().collect(Collectors.summingLong(o -> o.getBalance()));
        return totalSumOnAllAccounts - sumOfAllCredits;
    }


}

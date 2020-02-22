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
import java.util.List;

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
    @PostMapping(value = "/createCredit/{accountID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Credit createCredit(@PathVariable("accountID") final int accoundID, @RequestBody Credit submittedCredit){
        Customer customer = customerService.getCustomerById(accoundID);
        Credit credit = new Credit(
                submittedCredit.getOriginalTerm(),
                submittedCredit.getRemainingTerm(),
                submittedCredit.getOriginalCreditAmount(),
                submittedCredit.getRemainingCreditAmount(),
                customer);


        creditService.createCredit(credit);
        return credit;
    }


    @Transactional
    @PostMapping(value = "/createAccount/{accountID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(@PathVariable("accountID") final int accoundID, @RequestBody Account submittedAccount){
        Account account = new Account();
        Customer customer = customerService.getCustomerById(accoundID);
        account.setBalance(submittedAccount.getBalance());
        account.setCustomer(customer);
        accountService.createAccount(account);
        return account;
    }


}

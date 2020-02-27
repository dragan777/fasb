package com.fasb.service;


import com.fasb.model.Account;
import com.fasb.model.Posting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {



    @InjectMocks
    AccountService accountService;




    @Test
    public void testTransferMoney(){
        Account accountFrom = new Account();
        accountFrom.setBalance(1000l);

        Account accountTo = new Account();
        accountTo.setBalance(1000l);
        Posting posting = accountService.createPostingFromMoneyTransfer(accountFrom,accountTo, 100l);
        Assert.assertEquals("Transfer money value is equal", posting.getTransferMoneyValue(), Long.valueOf(100l));
        Assert.assertEquals("Id of account from is save", posting.getAccountFrom().getId(), accountFrom.getId());
        Assert.assertEquals("Id of account to is save", posting.getAccountTo().getId(), accountFrom.getId());
        Assert.assertEquals("Account from balance is reduced", accountTo.getBalance(), Long.valueOf(1100l));
        Assert.assertEquals("Account TO balance is increased", accountFrom.getBalance(), Long.valueOf(900l));
    }

}

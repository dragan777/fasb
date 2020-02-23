package com.fasb;

import com.fasb.model.Credit;
import com.fasb.model.Customer;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

public class MockDataCreatorUtil {




    public static  Customer createMockCustomer(TestEntityManager entityManager){
        Customer mockCustomer = new Customer("alex", "doe", "", 2);
        entityManager.persist(mockCustomer);
        entityManager.flush();
        return mockCustomer;
    }

    public static Credit createMockCreditForCustomer(TestEntityManager entityManager, Customer customer, boolean setExpiredDate){
        Credit mockCredit = new Credit();
        mockCredit.setRemainingTerm(10);
        mockCredit.setCustomer(customer);
        mockCredit.setOriginalCreditAmount(1000l);
        mockCredit.setRemainingCreditAmount(1000l);
        mockCredit.setOriginalTerm(10);

        //force mock credit with expiration date
        if(setExpiredDate){
            mockCredit.setExpirationDate(LocalDateTime.now().minusMonths(10));
        }else{
            mockCredit.setExpirationDate(LocalDateTime.now().plusMonths(10));
        }
        entityManager.persist(mockCredit);
        entityManager.flush();
        return mockCredit;
    }
}

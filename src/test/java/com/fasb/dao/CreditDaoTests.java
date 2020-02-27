package com.fasb.dao;


import com.fasb.MockDataCreatorUtil;
import com.fasb.model.Credit;
import com.fasb.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
public class CreditDaoTests {




    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CreditDao creditDao;



    @Test
    public void testFindByCustomerId() {
        Customer alex = MockDataCreatorUtil.createMockCustomer(entityManager);
        Credit creditForAlex = MockDataCreatorUtil.createMockCreditForCustomer(entityManager,alex, true);
        Credit secondDreditForAlex = MockDataCreatorUtil.createMockCreditForCustomer(entityManager,alex, false);
        List<Credit> credits = creditDao.findByCustomerId(alex.getId());
        Assert.assertTrue(credits.size() == 2);


    }


    @Test
    public void testExceedCredits() {
        Customer alex = MockDataCreatorUtil.createMockCustomer(entityManager);
        Credit creditForAlex = MockDataCreatorUtil.createMockCreditForCustomer(entityManager,alex, true);
        Credit secondDreditForAlex = MockDataCreatorUtil.createMockCreditForCustomer(entityManager,alex, false);

        List<Credit> exceededCredits = creditDao.findExceededCredits();
        Assert.assertTrue(exceededCredits.size() == 1);
        Assert.assertTrue(exceededCredits.get(0).getExpirationDate().isBefore(LocalDateTime.now()));

    }


}

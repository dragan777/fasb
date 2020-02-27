package com.fasb.dao;


import com.fasb.MockDataCreatorUtil;
import com.fasb.model.Account;
import com.fasb.model.Customer;
import com.fasb.model.Posting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
public class PostingDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostingDao postingDao;


    @Test
    public void testFindByDate(){
        Customer customer1 = MockDataCreatorUtil.createMockCustomer(entityManager);
        Account accountFrom = MockDataCreatorUtil.createMockAccount(entityManager, customer1, 2000l);
        Account accountTo = MockDataCreatorUtil.createMockAccount(entityManager,  customer1, 20001l);
        Posting posting1 = MockDataCreatorUtil.createMockPosting(entityManager, accountFrom, accountTo,LocalDateTime.parse("2020-01-01T00:00:00"));
        Posting posting2 = MockDataCreatorUtil.createMockPosting(entityManager, accountFrom, accountTo,LocalDateTime.parse("2020-01-01T23:59:59"));
        Posting posting3 = MockDataCreatorUtil.createMockPosting(entityManager, accountFrom, accountTo,LocalDateTime.parse("2020-03-01T23:59:59"));
        List<Posting> postingList = postingDao.findByDate(LocalDateTime.parse("2020-01-01T00:00:00"), LocalDateTime.parse("2020-01-01T23:59:59"));
        Assert.assertTrue(postingList.size() == 2);
    }


}

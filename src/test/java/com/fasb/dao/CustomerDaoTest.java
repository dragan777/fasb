package com.fasb.dao;


import com.fasb.MockDataCreatorUtil;
import com.fasb.dao.CustomerDao;
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
import java.util.List;


@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
public class CustomerDaoTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerDao customerDao;


    @Test
    public void testFindByLastName() {
        MockDataCreatorUtil.createMockCustomer(entityManager);

        List<Customer> found = customerDao.findByLastName("doe", Sort.by(Sort.Direction.DESC, "firstName"));
        Assert.assertTrue(found.size()==1);
    }
}

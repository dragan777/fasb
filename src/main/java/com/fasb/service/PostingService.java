package com.fasb.service;


import com.fasb.dao.PostingDao;
import com.fasb.model.Credit;
import com.fasb.model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostingService {


    @Autowired
    PostingDao postingDao;

    public Posting createPosting(Posting posting){
        postingDao.save(posting);
        return posting;
    }

}

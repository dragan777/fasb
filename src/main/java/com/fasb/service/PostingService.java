package com.fasb.service;


import com.fasb.dao.PostingDao;
import com.fasb.model.Credit;
import com.fasb.model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostingService {


    @Autowired
    PostingDao postingDao;

    public Posting createPosting(Posting posting){

        postingDao.save(posting);
        return posting;
    }

    public List<Posting> getPostingsByDate(String dateStr){
        LocalDate date = LocalDate.parse(dateStr);
        LocalDateTime dateFrom = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime dateTo = LocalDateTime.of(date, LocalTime.MAX);
        return postingDao.findByDate(dateFrom,dateTo);
    }

    public List<Posting> getAllPostings(Integer pageNo, Integer pageSize, String sortBy, String sortDir)
    {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if(sortDir.equals("desc")){
            sortDirection = Sort.Direction.DESC;
        }
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortDirection, sortBy));

        Page<Posting> pagedResult = postingDao.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}

package com.fasb.api;


import com.fasb.model.Posting;
import com.fasb.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/postings")
@RestController
public class PostingController {



    @Autowired
    PostingService postingService;




    @GetMapping
    public List<Posting> getPostingsByDate(@RequestParam String date){
        return postingService.getPostingsByDate(date);
    }

    @GetMapping(value="/all")
    public List<Posting> getAllPostings(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortBy, @RequestParam String sortDir){
        return postingService.getAllPostings(pageNo, pageSize, sortBy, sortDir);
    }
}

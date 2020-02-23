package com.fasb.dao;

import com.fasb.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingDao extends JpaRepository<Posting, Integer> {


}


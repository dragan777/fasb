package com.fasb.dao;

import com.fasb.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostingDao extends JpaRepository<Posting, Integer> {


    @Query("select p from Posting p where  p.bookingDate BETWEEN :startDate AND :endDate")
    List<Posting> findByDate(LocalDateTime startDate, LocalDateTime endDate);
}


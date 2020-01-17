package com.example.upp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.upp.model.Reviewer;

@Repository
public interface ReviewerRepository  extends JpaRepository<Reviewer, Long>{

}

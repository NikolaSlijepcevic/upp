package com.example.upp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.upp.model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long>{

	Magazine findOneById(long id);
	Magazine findOneByIssn(String issn);

}

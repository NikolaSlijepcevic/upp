package com.example.upp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.upp.model.Magazine;
import com.example.upp.repository.MagazineRepository;

@Service
public class MagazineServiceImpl implements MagazineService{

	@Autowired 
	private MagazineRepository magazineRepository;
	
	@Override
	public List<Magazine> getAll() {
		// TODO Auto-generated method stub
		return magazineRepository.findAll();
	}

	@Override
	public Magazine save(Magazine magazine) {
		// TODO Auto-generated method stub
		return magazineRepository.save(magazine);
	}

	@Override
	public Magazine findMagazineById(long id) {
		// TODO Auto-generated method stub
		return magazineRepository.findOneById(id);
	}

	@Override
	public void deleteMagazine(Magazine magazine) {
		// TODO Auto-generated method stub
		magazineRepository.delete(magazine);
		
	}

	@Override
	public Magazine findMagazineByIssn(String issn) {
		// TODO Auto-generated method stub
		return magazineRepository.findOneByIssn(issn);
	}

}

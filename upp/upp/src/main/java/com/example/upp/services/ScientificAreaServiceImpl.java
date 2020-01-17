package com.example.upp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.upp.model.ScientificArea;
import com.example.upp.repository.ScientificAreaRepository;

@Service
public class ScientificAreaServiceImpl implements ScientificAreaService {
	
	@Autowired
	private ScientificAreaRepository areaRepository;
	
	@Override
	public List<ScientificArea> getAll() {
		// TODO Auto-generated method stub
		return areaRepository.findAll();
	}

	@Override
	public ScientificArea save(ScientificArea area) {
		// TODO Auto-generated method stub
		return areaRepository.save(area);
	}

}

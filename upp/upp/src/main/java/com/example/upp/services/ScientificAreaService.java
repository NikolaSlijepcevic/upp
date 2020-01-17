package com.example.upp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.upp.model.ScientificArea;

@Service
public interface ScientificAreaService {
	public List<ScientificArea> getAll();
	public ScientificArea save(ScientificArea area);

}

package com.example.upp.services;

import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.upp.model.FormSubmissionDTO;
import com.example.upp.model.Magazine;
import com.example.upp.model.ScientificArea;

@Service
public class SavingMagazineService implements JavaDelegate{
	@Autowired
	IdentityService identityService;
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	ScientificAreaService scienceService;
	@Autowired
	MagazineService magazineService;
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Cuvanje magazina");
		 List<FormSubmissionDTO> userInfo = (List<FormSubmissionDTO>)execution.getVariable("magazineData");
		 System.out.println(userInfo); 
		 System.out.println(userInfo.toString()); 
		 Magazine newMagazine = new Magazine();
		 for(FormSubmissionDTO item: userInfo){
			  System.out.println("***************");
			  String fieldId = item.getFieldId();
			  String fieldValue = item.getFieldValue();
			  if(fieldId.equals("title")){
				  newMagazine.setTitle(fieldValue);
			  }
			  if(fieldId.equals("issn")){
				  newMagazine.setIssn(fieldValue);
			  }
			  if(fieldId.equals("paying")){
				  newMagazine.setOpenaccess(false);
				  if(fieldValue.equals("true")){
					  newMagazine.setOpenaccess(true);
				  }
			  }
			  if(fieldId.equals("areas")){
				  List<ScientificArea> allAreas = scienceService.getAll();
				  for(ScientificArea area : allAreas){
					  for(String selectedArea:item.getCategories()){

						  System.out.println("Area sa id je "+selectedArea);
						  String idS=area.getId().toString();
						  if(idS.equals(selectedArea)){
							  System.out.println("Pronadjena oblast");
							  System.out.println(area.getName());
							  newMagazine.getMagazineAreas().add(area);
							  
						  }
					  }
				  }

			  }

		 }
		 magazineService.save(newMagazine);
	}
	}

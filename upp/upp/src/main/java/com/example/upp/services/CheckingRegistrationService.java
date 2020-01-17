package com.example.upp.services;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.upp.model.FormSubmissionDTO;

public class CheckingRegistrationService implements JavaDelegate{

	@Autowired
	IdentityService identityService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		 
	      List<FormSubmissionDTO> userData = (List<FormSubmissionDTO>)execution.getVariable("registrationData");
	     
	      boolean valid = true;
	      for(FormSubmissionDTO item: userData){
				String fieldId = item.getFieldId();
				if(fieldId.equals("branches")){					
					ArrayList<String> selectedBranches = item.getCategories();
					if(selectedBranches.size()==0){
						valid = false;
					}
				}
				if(!fieldId.equals("title") &&  !fieldId.equals("reviserFlag") && !fieldId.equals("branches")){
					if(item.getFieldValue()==null || item.getFieldValue().equals("") || item.getFieldValue().isEmpty()==true){
						valid = false;
					}
				}
			}
	      
	     execution.setVariable("validInput", valid);
	      
	}

}

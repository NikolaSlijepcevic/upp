package com.example.upp.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.upp.model.FormSubmissionDTO;
import com.example.upp.model.ScientificArea;
@Service
public class SaveNewUserService implements JavaDelegate{

	
	@Autowired
	IdentityService identityService;
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	ScientificAreaService scienceService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Usao u saveNewUser servis");
	
		 List<FormSubmissionDTO> userInfo = (List<FormSubmissionDTO>)execution.getVariable("registrationData");

		 User user = identityService.newUser("NoviUser");
		 com.example.upp.model.User newUser = new com.example.upp.model.User();  
		 System.out.println("Usao u savenewuser");
		 
		 for(FormSubmissionDTO item: userInfo){
			  String fieldId = item.getFieldId();
			  String fieldValue = item.getFieldValue();
			 
			  if(fieldId.equals("name")){
				  user.setFirstName(fieldValue);
				  newUser.setName(fieldValue);
			  }else if(fieldId.equals("surname")){
				  user.setLastName(fieldValue);
				  newUser.setSurname(fieldValue);
			  }else if(fieldId.equals("email")){
				  user.setEmail(fieldValue);
				  newUser.setMail(fieldValue);
			  }else if(fieldId.equals("password")){
				  String encrPass = "";
				  try {
					  encrPass = userService.encrypt(fieldValue);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						encrPass=fieldValue;
						e.printStackTrace();
					}
				    
				  user.setPassword(encrPass);
				  newUser.setPassword(encrPass);
			  }else if(fieldId.equals("city")){
				  newUser.setCity(fieldValue);
			  }else if(fieldId.equals("country")){
				  newUser.setCountry(fieldValue);
			  }else if(fieldId.equals("title")){
				  newUser.setTitle(fieldValue);
			  }else if(fieldId.equals("reviewerFlag")){
				  newUser.setReviewerFlag(false);
				  if(fieldValue.equals("true")){
					  newUser.setReviewerFlag(true);
				  }
			  }else if(fieldId.equals("username")){ 
				  System.out.println("Username je ");
				  newUser.setUsername(fieldValue);
				  user.setId(fieldValue);
			  }else if(fieldId.equals("branches")){
				  System.out.println("Naucne oblasti su");
				  List<ScientificArea> allAreas = scienceService.getAll();
				  for(ScientificArea area : allAreas){
					  for(String selectedArea:item.getCategories()){

						  String idS=area.getId().toString();
						  if(idS.equals(selectedArea)){
							  System.out.println("Pronadjena oblast");
							  System.out.println(area.getName());
							  newUser.getScientificAreas().add(area);
							  
						  }
					  }
				  }
			  }else{
				  System.out.println("Nista od ponudjenog");
			  }
		  }
		
	      identityService.saveUser(user);
	      userService.save(newUser);
	}

}

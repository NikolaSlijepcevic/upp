package com.example.upp.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.upp.model.User;
import com.example.upp.model.UserDTO;
import com.example.upp.services.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	
	
	@RequestMapping(value="/getAll", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<User>> getAllUsers(){		

	return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
	}
	@RequestMapping(value="/getAllReviewers", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<UserDTO>> getAllReviewers(){		
		System.out.println("Usao u getAllReviewers");
		List<User> users = userService.getAll();
		List<User> reviewers = new ArrayList<User>();
		for(User u : users){
			if(u.getType().equals("reviewer")){
				reviewers.add(u);
			}
		}
		List<UserDTO> list=new ArrayList<UserDTO>();
		for(User e : reviewers){
			list.add(new UserDTO(e.getId(),e.getName(),e.getSurname()));
		}
		return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
	}
	@RequestMapping(value="/getAllEditors", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<UserDTO>> getAllEditors(){		
		List<User> users = userService.getAll();
		
		List<User> editors = new ArrayList<User>();
		for(User u : users){
			if(u.getType().equals("editor")){
					editors.add(u);
				}
		}
		List<UserDTO> list=new ArrayList<UserDTO>();
		for(User e : editors){
			list.add(new UserDTO(e.getId(),e.getName(),e.getSurname()));
		}
		return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
	}


	@RequestMapping(value="/login",method = RequestMethod.POST)
    public  ResponseEntity<User>  loginUser(@RequestBody User user, @Context HttpServletRequest request){
	  
       String encryptedPass = "";
       String username = user.getUsername();
       String password = user.getPassword();

       User loginUser = userService.findUserByUsername(username);
       if(loginUser == null){
    	    
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
       }
	   if(!loginUser.isActive()){
   	        
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

	   } 
	   	try {
			encryptedPass = userService.encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!loginUser.getPassword().equals(encryptedPass)) {
			//moraju se poklapati unesena lozinka i lozinka od korisnika sa unetim mailom 
			
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

		}
		
		request.getSession().setAttribute("logged", loginUser);

        System.out.println(username + " , " + password);
		return new ResponseEntity<User>(loginUser, HttpStatus.OK);
    }
	@RequestMapping(value="/logout",
			method = RequestMethod.GET)
	public User logoutUser(@Context HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("ulogovan");		
		
		request.getSession().invalidate();
		if(user == null) {
			return null;
		}
		return user;
	}

	public boolean checkMail(String mail) {
		if(mail.isEmpty()) {
			return false;
		}
		if(mail.contains(";")) {
			return false;
		}
		
		if(mail.contains(",")) {
			return false;
		}
		for(Character c:mail.toCharArray()) {
			if(Character.isWhitespace(c)) {
				return false;
			
			}
				
		}
		return true;
	}
	
}

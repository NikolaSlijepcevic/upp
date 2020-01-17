 package com.example.upp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "mail", nullable = false)
	private String mail;

	@Column(name = "title")
	private String title;
	
	@Column(name="revFlag")
	private boolean reviewerFlag;
	
	@Column(name = "active")
	private boolean active;

	@Column(name = "type")
	private String type;

	@ManyToMany
	@JoinTable(
	        name = "User_Areas", 
	        joinColumns = { @JoinColumn(name = "user_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "scientificArea_id") }
	  )
	private Set<ScientificArea> interestedAreas = new HashSet<ScientificArea>();
	
	@ManyToMany(mappedBy = "reviewerMagazine")
	private Set<Magazine> reviewedMagazines = new HashSet<Magazine>();
	
	@ManyToMany(mappedBy = "editorMagazine")
	private Set<Magazine> editedMagazines = new HashSet<Magazine>();
	
	public User(){
		this.active = false;
		this.type = "user";
		
	}

	public User(Long id, String name, String surname, String city,
			String country, String username, String password, String mail,
			String title, boolean reviewerFlag, boolean active, String type) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.country = country;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.title = title;
		this.reviewerFlag = reviewerFlag;
		this.active = active;
		this.type = type;
	}

	public User(Long id, String name, String surname, String city,
			String country, String username, String password, String mail,
			String title, boolean reviewerFlag) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.country = country;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.title = title;
		this.reviewerFlag = reviewerFlag;
	}

	public User(Long id, String name, String surname, String city,
			String country, String username, String password, String mail,
			String title, boolean reviewerFlag,
			Set<ScientificArea> scientificAreas) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.country = country;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.title = title;
		this.reviewerFlag = reviewerFlag;
		this.interestedAreas = scientificAreas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<ScientificArea> getInterestedAreas() {
		return interestedAreas;
	}

	public void setInterestedAreas(Set<ScientificArea> interestedAreas) {
		this.interestedAreas = interestedAreas;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isReviewerFlag() {
		return reviewerFlag;
	}

	public void setReviewerFlag(boolean reviewerFlag) {
		this.reviewerFlag = reviewerFlag;
	}

	public Set<ScientificArea> getScientificAreas() {
		return interestedAreas;
	}

	public void setScientificAreas(Set<ScientificArea> scientificAreas) {
		this.interestedAreas = scientificAreas;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Magazine> getReviewedMagazines() {
		return reviewedMagazines;
	}

	public void setReviewedMagazines(Set<Magazine> reviewedMagazines) {
		this.reviewedMagazines = reviewedMagazines;
	}

	public Set<Magazine> getEditedMagazines() {
		return editedMagazines;
	}

	public void setEditedMagazines(Set<Magazine> editedMagazines) {
		this.editedMagazines = editedMagazines;
	}
	
	
	
		
	
	
}

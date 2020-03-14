package com.iyasu.addressbookfinal.person;

public class Person {
   private int id;
   private String firstName;
   private String lastName;
   private String street;
   private String city;
   private String state;
   private String country;
   private String postalCode;
   
   public String getStreet() {
	return street;
}


public void setStreet(String street) {
	this.street = street;
}


public String getCity() {
	return city;
}


public void setCity(String city) {
	this.city = city;
}


public String getState() {
	return state;
}


public void setState(String state) {
	this.state = state;
}


public String getCountry() {
	return country;
}


public void setCountry(String country) {
	this.country = country;
}


public String getPostalCode() {
	return postalCode;
}


public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}





public Person(int id, String firstName, String lastName, String street, String city, String state, String country,
		String postalCode) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.street = street;
	this.city = city;
	this.state = state;
	this.country = country;
	this.postalCode = postalCode;
}


public int getId() {
	return id;
}


public String getFirstName() {
	return firstName;
}

public String getLastName() {
	return lastName;
}


@Override
public String toString() {
	return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", city="
			+ city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode + "]";
}




   
}


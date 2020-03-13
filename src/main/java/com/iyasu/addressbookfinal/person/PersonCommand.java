package com.iyasu.addressbookfinal.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.iyasu.addressbookfinal.database.Pair;

public class PersonCommand  {
	private PersonStore store;
	List<String> parts = new ArrayList<>();
	public PersonCommand(PersonStore store) {
		
		this.store = store;
	}

	

	public String perform(String input) {
		System.out.println("input :" +input);
		
		List<String> parts2 = new ArrayList<String>();
		String[] inputSplit = input.split(",");
        String commandString = inputSplit[0]; 
        for (int i =1 ;i< inputSplit.length;i++) {
        	System.out.println(i + ":"+ inputSplit[i]);
        	parts2.add(inputSplit[i]);
        }
        
		String result = " " ;
		//parts.remove(0);
		
		switch (commandString) {

		case "create":
			result = doCreate(parts2);
			break;
		case "get":
			result =  doGet(parts2);
			break;
		case "update":
			 result = doUpdate(parts2);
			break;
		case "remove":
			 result = doRemove( parts2);
			break;
		case "list":
			  result = doList();
			break;

		default:
			break;
		}
		
		return result;
	}
	

	private String doList() {
		
		System.out.println("do list");
		
		List<Person> personList = store.getAll();
		StringBuilder sb = new StringBuilder();
		for (Person p : personList) {
			sb.append(p.toString());
		}
		
		return sb.toString();
	}
	private String doGet(List<String> parts) {
		
		
		for(String part :parts) {
			System.out.println(part);
		}
		System.out.println("do get");
		Integer id = Integer.valueOf(parts.get(0));
		Person p = store.get(id);
	
		return p != null ? p.toString():"";
	}
	private String doCreate(List<String> parts) {
		Person person = store.create(parts);
		return person != null ? person.toString() : " ";
	}
	private String doUpdate(List<String> parts) {
		System.out.println("do update");
		int id = Integer.valueOf(parts.get(0));
		parts.remove(0);
		Person person = store.update(id, parts);
		return person != null ? person.toString():"";
	}
	
	private String doRemove( List<String> parts) {
		System.out.println("do remove");
		int id = Integer.valueOf(parts.get(0));
		store.remove(id);
		return null;
	}

}

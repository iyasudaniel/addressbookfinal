package com.iyasu.addressbookfinal.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.iyasu.addressbookfinal.database.Database;
import com.iyasu.addressbookfinal.database.Pair;

public class PersonStore  {
   
	private Database db;
    private  static String table = "person";
    private  static List<String> fields = new ArrayList<>();
    
  
	

	public PersonStore(Database db) {
		this.db = db;

	}
	public Person create(List<String> values) {
		List<Pair<String, String>> valuePairs = constructPairs(values);
	
		int id = db.insert(table, valuePairs);
		return get(id);
	}
	private List<Pair<String, String>> constructPairs(List<String> values) {
		List<Pair<String, String>> valuePairs = new ArrayList<Pair<String, String>>();
		for (int i=0;i< values.size();i++) {
			Pair pair = null ;
			if(i==0) {
				pair= Pair.of("firstName", values.get(i));
			}else if(i==1) {
				pair= Pair.of("lastName", values.get(i));

			}
			else if (i == 2) {
				pair= Pair.of("street", values.get(i));

			}
			else if (i == 3) {
				pair= Pair.of("city", values.get(i));

			}
			else if (i == 4) {
				pair= Pair.of("state", values.get(i));

			}
			else if (i == 5) {
				pair= Pair.of("country", values.get(i));

			}
			else if (i == 6) {
				pair= Pair.of("postalCode", values.get(i));

			}

			valuePairs.add(pair);
			 
		}
		return valuePairs;
	}
	public Person get(int id) {
		List<String> personValues = db.get(table, id);
		if( personValues != null && !personValues.isEmpty())
			return new Person(id, personValues.get(1), personValues.get(2), personValues.get(3), 
					personValues.get(4), personValues.get(5), personValues.get(6),personValues.get(7) );
		else
			return null;

	}
	public List<Person> getAll(){ 
		List<Person> personList = new ArrayList<>();
		List<List<String>> personStrings = db.getAll(table);
		for (List<String> pss: personStrings) {
			int id = Integer.valueOf(pss.get(0));
			personList.add(new Person(id, pss.get(1), pss.get(2), pss.get(3), pss.get(4), pss.get(5), pss.get(6), pss.get(7)));
		}
		return personList;
	}
	public Person update(int id, List<String> values ) {
		db.update(table, id, constructPairs(values));
		return get(id);
	}
	public void remove(int id) {
		db.remove(table, id);
		
	}

}

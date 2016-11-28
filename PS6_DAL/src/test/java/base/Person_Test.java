package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
		System.out.println("add person");
		PersonDAL.addPerson(person1);
	}
	
	
	@Test
	public void updatePerson_Test(){
		System.out.println("update person");
		person1.setFirstName("Alani");
		person1.setLastName("Johnson");
		PersonDAL.updatePerson(person1);
	}
	
	@Test
	public void getPersons_Test(){
		System.out.println("get multiple persons");
		ArrayList<PersonDomainModel> PersonList = PersonDAL.getPersons();
		boolean bool = false;
		for (PersonDomainModel person:PersonList){
			if (person.getPersonID().equals(person1.getPersonID())){
				bool = true;
			}
		}
		assertTrue(bool);
	}
	
	@Test
	public void getPerson_Test(){
		System.out.println("get person");
		assertEquals(true,PersonDAL.getPerson(person1UUID).getPersonID().equals(person1UUID));
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
		System.out.println("tear down");
		PersonDAL.deletePerson(person1UUID);
	}

}

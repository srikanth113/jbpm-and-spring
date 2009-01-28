package ccp.rtd.entities;

import java.io.Serializable;

public class Prisoner implements Serializable{
	private Integer prisonerID;
	private String firstName;
	private String lastName;
	private PrisonerData prisonerData;
	
	public Prisoner()
	{
		
	}
	
	public Prisoner(String firstName,String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Prisoner(String firstName,String lastName,PrisonerData prisonerData)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.prisonerData = prisonerData;
	}

	public Integer getPrisonerID() {
		return prisonerID;
	}

	public void setPrisonerID(Integer prisonerID) {
		this.prisonerID = prisonerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public PrisonerData getPrisonerData() {
		return prisonerData;
	}

	public void setPrisonerData(PrisonerData prisonerData) {
		this.prisonerData = prisonerData;
	}
	
	
}

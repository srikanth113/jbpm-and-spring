package ccp.rtd.entities;

import java.io.Serializable;

public class PrisonerRelative implements Serializable{
	private Integer prisonerRelativeID;
	private String name;
	private String surname;
	private String description;
	private TypeRelative typeRelative ;
	private PrisonerData prisonerData;
	
	public PrisonerRelative(){}
	
	public PrisonerRelative(String description)
	{
		this.description = description;
	}
	
	public PrisonerRelative(String description,TypeRelative typeRelative)
	{
		this.description = description;
		this.typeRelative = typeRelative;
	}
	
	public PrisonerRelative(String name,String surname,String description,TypeRelative typeRelative,PrisonerData prisonerData)
	{
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.typeRelative = typeRelative;
		this.prisonerData = prisonerData;
	}
	
	public PrisonerRelative(PrisonerData prisonerData,TypeRelative typeRelative)
	{
		this.prisonerData = prisonerData;
		this.typeRelative = typeRelative;
	}
	
	

	public Integer getPrisonerRelativeID() {
		return prisonerRelativeID;
	}

	public void setPrisonerRelativeID(Integer prisonerRelativeID) {
		this.prisonerRelativeID = prisonerRelativeID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeRelative getTypeRelative() {
		return typeRelative;
	}

	public void setTypeRelative(TypeRelative typeRelative) {
		this.typeRelative = typeRelative;
	}

	public PrisonerData getPrisonerData() {
		return prisonerData;
	}

	public void setPrisonerData(PrisonerData prisonerData) {
		this.prisonerData = prisonerData;
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
	
}

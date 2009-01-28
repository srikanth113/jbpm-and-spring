package ccp.rtd.entities;

import java.io.Serializable;


public class PrisonerHasType implements Serializable{
	private Integer prisonerHasTypeID;
	private String description;
	private TypePrisoner typePrisoner;
	private PrisonerData prisonerData;
	
	public PrisonerHasType()
	{}
	
	public PrisonerHasType(String description)
	{
		this.description = description;
	}
	
	public PrisonerHasType(String description,TypePrisoner typePrisoner)
	{
		this.description = description;
		this.typePrisoner = typePrisoner;
	}
	
	public PrisonerHasType(String description,TypePrisoner typePrisoner,PrisonerData prisonerData)
	{
		this.description = description;
		this.typePrisoner = typePrisoner;
		this.prisonerData = prisonerData;
	}
	
	public PrisonerHasType(PrisonerData prisonerData,TypePrisoner typePrisoner)
	{
		this.prisonerData = prisonerData;
		this.typePrisoner = typePrisoner;
	}

	public Integer getPrisonerHasTypeID() {
		return prisonerHasTypeID;
	}

	public void setPrisonerHasTypeID(Integer prisonerHasTypeID) {
		this.prisonerHasTypeID = prisonerHasTypeID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypePrisoner getTypePrisoner() {
		return typePrisoner;
	}

	public void setTypePrisoner(TypePrisoner typePrisoner) {
		this.typePrisoner = typePrisoner;
	}

	public PrisonerData getPrisonerData() {
		return prisonerData;
	}

	public void setPrisonerData(PrisonerData prisonerData) {
		this.prisonerData = prisonerData;
	}
	
	
}

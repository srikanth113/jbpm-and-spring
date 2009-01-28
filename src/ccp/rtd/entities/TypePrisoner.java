package ccp.rtd.entities;

import java.io.Serializable;

public class TypePrisoner implements Serializable{
	private Integer typePrisonerID;
	private String name;
	private String subDesc;
	
	public TypePrisoner()
	{}
	
	public TypePrisoner(String name,String subDesc)
	{
		this.name = name;
		this.subDesc = subDesc;
	}

	public Integer getTypePrisonerID() {
		return typePrisonerID;
	}

	public void setTypePrisonerID(Integer typePrisonerID) {
		this.typePrisonerID = typePrisonerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubDesc() {
		return subDesc;
	}

	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}
	
	
}

package ccp.rtd.entities;

import java.io.Serializable;

public class TypeRelative implements Serializable{
	private Integer typeReletiveID;
	private String name;
	
	public TypeRelative()
	{
	}
	
	public TypeRelative(String name)
	{
		this.name = name;
	}

	public Integer getTypeReletiveID() {
		return typeReletiveID;
	}

	public void setTypeReletiveID(Integer typeReletiveID) {
		this.typeReletiveID = typeReletiveID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

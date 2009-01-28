package ccp.rtd.entities;

import java.io.Serializable;

public class TypeSickness implements Serializable{
	private Integer typeSicknessID;
	private String name;
	
	public TypeSickness()
	{}
	
	public TypeSickness(String name)
	{
		this.name = name;
	}

	public Integer getTypeSicknessID() {
		return typeSicknessID;
	}

	public void setTypeSicknessID(Integer typeSicknessID) {
		this.typeSicknessID = typeSicknessID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

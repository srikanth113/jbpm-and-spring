package ccp.rtd.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PrisonerData implements Serializable {
	private String prisonerDataID;
	private String realFirstName;
	private String realLastName;
	private String sex;
	private Set prisoners = new HashSet();
	
	public PrisonerData()
	{
	}
	
	public PrisonerData(String prisonerDataID,String realFirstName,String realLastName)
	{
		this.prisonerDataID = prisonerDataID;
		this.realFirstName = realFirstName;
		this.realLastName = realLastName;
	}
	//for prisonerInfo
	public PrisonerData(String sex)
	{
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPrisonerDataID() {
		return prisonerDataID;
	}

	public void setPrisonerDataID(String prisonerDataID) {
		this.prisonerDataID = prisonerDataID;
	}

	public String getRealFirstName() {
		return realFirstName;
	}

	public void setRealFirstName(String realFirstName) {
		this.realFirstName = realFirstName;
	}

	public String getRealLastName() {
		return realLastName;
	}

	public void setRealLastName(String realLastName) {
		this.realLastName = realLastName;
	}

	public Set getPrisoners() {
		return prisoners;
	}

	public void setPrisoners(Set prisoners) {
		this.prisoners = prisoners;
	}
	
	
}

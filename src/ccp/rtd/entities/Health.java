package ccp.rtd.entities;

import java.io.Serializable;

public class Health implements Serializable{
	private Integer healthID;
	private String healthDesc;
	private String opinion;
	private Doctor doctor;
	private PrisonerData prisonerData;
	private TypeSickness typeSickness;
	
	public Health()
	{}
	
	public Health(String healthDesc,String opinion,Doctor doctor,PrisonerData prisonerData,TypeSickness typeSickness)
	{
		this.healthDesc = healthDesc;
		this.opinion = opinion;
		this.doctor = doctor;
		this.prisonerData = prisonerData;
		this.typeSickness = typeSickness;
	}

	public Integer getHealthID() {
		return healthID;
	}

	public void setHealthID(Integer healthID) {
		this.healthID = healthID;
	}

	public String getHealthDesc() {
		return healthDesc;
	}

	public void setHealthDesc(String healthDesc) {
		this.healthDesc = healthDesc;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public PrisonerData getPrisonerData() {
		return prisonerData;
	}

	public void setPrisonerData(PrisonerData prisonerData) {
		this.prisonerData = prisonerData;
	}

	public TypeSickness getTypeSickness() {
		return typeSickness;
	}

	public void setTypeSickness(TypeSickness typeSickness) {
		this.typeSickness = typeSickness;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	
}

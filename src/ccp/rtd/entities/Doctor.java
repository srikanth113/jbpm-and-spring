package ccp.rtd.entities;

import java.io.Serializable;

public class Doctor implements Serializable{
	
	private Integer doctorID;
	private String name;
	private String surname;
	
	public Doctor()
	{
	}
	
	public Doctor(String name,String surname)
	{
		this.name = name;
		this.surname = surname;
	}

	public Integer getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
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

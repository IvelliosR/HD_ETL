package pl.danych.hurtownie.objects;


import javax.persistence.*;

@Entity
@Table
public class Remark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name = "";
	private String value = "";


	//klasa typu entity nie moze posiadac konstruktora z argumentami
//	public Remark(String name, String value) {
//		this.name = name;
//		this.value = value;
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

package pl.danych.hurtownie.objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private String brand;
	private String model;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_remark", nullable = false)
	private List<Remark> remarks;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_comment", nullable = false)
	private List<Comment> comments;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public List<Remark> getRemarks() {
		return remarks;
	}
	
	public void setRemarks(List<Remark> remarks) {
		this.remarks = remarks;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public int countComments() {
		return this.comments.size();
	}
	
	public int countRemarks() {
		return this.remarks.size();
	}
}

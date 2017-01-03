package pl.danych.hurtownie.objects;

import javafx.beans.property.SimpleStringProperty;

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

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_product", nullable = false)
	private List<Remark> remarks;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_product", nullable = false)
	private List<Comment> comments;

	public int getId(){
		return id;
	}
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

	@Override
	public boolean equals(Object object){
		if(object == null)
			return false;
		if(object == this)
			return true;
		if(object instanceof Product){
			Product product  = (Product) object;
			if(type.equals(product.getType()) && brand.equals(product.getBrand())
					&& model.equals(product.getModel()))
				return true;
		}
		return false;
	}
}

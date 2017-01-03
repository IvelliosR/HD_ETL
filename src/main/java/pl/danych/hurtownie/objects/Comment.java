package pl.danych.hurtownie.objects;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ElementCollection
	@CollectionTable(name = "cons", joinColumns = @JoinColumn(name = "id_comment"))
	@Column(name = "cons")
	private List<String> cons;

	@ElementCollection
	@CollectionTable(name = "pros", joinColumns = @JoinColumn(name = "id_comment"))
	@Column(name = "pros")
	private List<String> pros;

	@Column(columnDefinition = "text")
	private String summary;
	private float rate;

	private String author = "anonim";
	private String create_date;
	private boolean recommend;
	private int helpful;
	private int unhelpful;
	
	public List<String> getCons() {
		return cons;
	}
	
	public void setCons(List<String> cons) {
		this.cons = cons;
	}
	
	public List<String> getPros() {
		return pros;
	}
	
	public void setPros(List<String> pros) {
		this.pros = pros;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public float getRate() {
		return rate;
	}
	
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getCreateDate() {
		return create_date;
	}
	
	public void setCreateDate(String create_date) {
		this.create_date = create_date;
	}
	
	public boolean isRecommend() {
		return recommend;
	}
	
	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
	
	public int getHelpful() {
		return helpful;
	}
	
	public void setHelpful(int helpful) {
		this.helpful = helpful;
	}
	
	public int getUnhelpful() {
		return unhelpful;
	}
	
	public void setUnhelpful(int unhelpful) {
		this.unhelpful = unhelpful;
	}

	@Override
	public boolean equals(Object object){
		if(object == null)
			return false;
		if(object == this)
			return true;
		if(object instanceof Comment){
			Comment comment = (Comment) object;
			if(summary.equals(comment.getSummary()) && rate == comment.getRate()
					&& author.equals(comment.getAuthor()) && create_date.equals(comment.getCreateDate())
					&& helpful == comment.getHelpful() && unhelpful == comment.getUnhelpful())
				return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}
}

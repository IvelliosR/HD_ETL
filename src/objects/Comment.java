package objects;

import java.util.List;

public class Comment {

	private List<String> cons;
	private List<String> pros;
	private String summary;
	private float rate;
	private String author;
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
}

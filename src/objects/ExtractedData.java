package objects;

import java.util.List;
import java.util.Date;
import org.jsoup.nodes.Document;

public class ExtractedData {
	private int current_position = 0;
	private Date start_date;
	private Date end_date;
	private List<Document> pages;
	
	public ExtractedData(List<Document> pages, Date start_date, Date end_date) {
		this.pages = pages;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public boolean hasNext() {
		if(this.current_position <= (this.pages.size() - 1)) {
			return true;
		}
		return false;
	}
	
	public Document getNext() {
		this.current_position = this.current_position + 1;
		return pages.get(this.current_position - 1);
	}
	
	public int getPagesQuantity() {
		return pages.size();
	}
	
	public Date getStartDate() {
		return this.start_date;
	}
	
	public Date getEndDate() {
		return this.end_date;
	}
	
	public void reset() {
		this.current_position = 0;
	}
}

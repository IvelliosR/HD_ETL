package objects;

import java.util.*;
import org.jsoup.nodes.Document;

public class ExtractedData {
	private int current_position = 0;
	private List<Document> pages;
	
	public ExtractedData(List<Document> pages) {
		this.pages = pages;
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
	
	public void reset() {
		this.current_position = 0;
	}
}

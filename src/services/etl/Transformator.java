package services.etl;

import org.jsoup.nodes.Document;
import java.util.*;
import objects.ExtractedData;
import objects.Product;
import objects.Comment;

public class Transformator {
	
	public Product transform(ExtractedData extractedData) {
		Product product = null;
		
		product = this.transformProductData(extractedData.getNext());
//		product.setComments(this.transformAllCommentsData(extractedData));
		
		return product;
	}
	
	public Product transformProductData(Document page) {
		Product product = new Product();

		product.setType(page.select(".breadcrumbs").select(".breadcrumb").last().select("a").select("span").text());
		
		return product;
	}
	
	public List<Comment> transformCommentsData(Document page) {
		List<Comment> comments = new ArrayList<Comment>();
		
		return comments;
	}
	
	public List<Comment> transformAllCommentsData(ExtractedData extractedData) {
		List<Comment> comments = new ArrayList<Comment>();
		
		extractedData.reset();
		while(extractedData.hasNext()) {
			comments.addAll(this.transformCommentsData(extractedData.getNext()));
		}
		
		return comments;
	}
	
}
